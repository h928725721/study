package com.candu.concurrency.part1.safe;

import java.rmi.UnexpectedException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class PutTakeTest {
    private static final ExecutorService pool = Executors.newCachedThreadPool();
    private final AtomicInteger putSum = new AtomicInteger(0);
    private final AtomicInteger takeSum = new AtomicInteger(0);
    /**
     * 线程的创建与启动需要很大的开销，如果线程执行时间很短，那么很可能会演变成串行执行
     * 使用CyclicBarrier  可以使一组线程同时执行或同时完成
     * 初始化时将计数值指定为工作者线程数量 * 2 +1，并在运行开始和结束时，使工作者线程和测试线程都在这个栅栏处等待。这能确保所有线程在开始执行任何工作之前，都首先执行到同一个位置。
     */
    private final CyclicBarrier barrier;
    private final BarrierTimer timer;
    private final BoundedBuffer<Integer> bb;
    private final int nTrials, nPairs;

    public static void main(String[] args) {
        new PutTakeTest(10, 10, 100000).test();
        pool.shutdown();
    }
    public PutTakeTest(int capacity, int npairs, int ntrials) {
        this.bb = new BoundedBuffer<>(capacity);
        this.barrier = new CyclicBarrier(npairs * 2 + 1);
        this.nTrials = ntrials;
        this.nPairs = npairs;
        this.timer = new BarrierTimer();
    }

    void test() {
        try {
            timer.clear();
            for (int i = 0; i < nPairs; i++) {
                pool.execute(new Producer());
                pool.execute(new Consumer());
            }
            barrier.await();
            barrier.await();
            long nsPerItem = timer.getTime() / (nPairs * (long)nTrials);
            System.out.println("Throughput: " + nsPerItem + " ns/item");
            if (putSum.get() != takeSum.get()) throw new UnexpectedException("结果不对");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    class Producer implements Runnable {
        ConcurrentHashMap
        @Override
        public void run() {

            try {
                System.out.println("Producer begin");
                int seed = (this.hashCode() ^ (int)System.nanoTime());
                int sum = 0;
                barrier.await();
                for (int i = nTrials; i > 0; --i) {
                    bb.put(seed);
                    sum += seed;
                    seed = xorShift(seed);
                }
                putSum.getAndAdd(sum);
                barrier.await();
                System.out.println("Producer end");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("Consumer begin");
                barrier.await();
                int sum = 0;
                for (int i = nTrials; i > 0; --i) {
                    sum += bb.take();
                }
                takeSum.getAndAdd(sum);
                barrier.await();
                System.out.println("Consumer end");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    /**
     * 要测试所有要点，就一定不能让编译器可以预先猜测到检验和的值。
     * 要避免这个问题，可以采用随机方式生成的测试数据
     */
    static int xorShift(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return y;
    }
}
