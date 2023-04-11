package com.candu.concurrency.part1.safe;

import java.util.Vector;
import java.util.concurrent.BlockingQueue;

public class WorkerThread extends Thread {
    private final BlockingQueue<Runnable> queue;
    public WorkerThread(BlockingQueue<Runnable> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        while (true) {
            try {
                Runnable take = queue.take();
                take.run();
            } catch (InterruptedException e) {
                break;
            }
        }
    }
    //每次add和toString都会获取/释放锁，至少4次
    public String getStoogeNames() {
        Vector<String> stooges = new Vector<>();
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
        return stooges.toString();
    }

}
