package com.candu.concurrency.part1.safe;

/**
 * put如果缓存满了，会阻塞其他put操作，take同理
 * 每次循环休眠一段时间，减轻cpu压力
 */
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V>{
    protected SleepyBoundedBuffer(int capacity) {
        super(capacity);
    }
    public void put(V v) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isFull()) {
                    doPUt(v);
                    return;
                }
            }
            Thread.sleep(3000);
        }
    }
    public V take() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isEmpty()) {
                    return doTake();
                }
            }
            Thread.sleep(3000);
        }
    }
}
