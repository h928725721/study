package com.candu.concurrency.part1.safe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {

    public static void main(String[] args) {
        TimingThreadPool threadPool = new TimingThreadPool(10, 20, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        for (int i = 0; i < 100; i++) {
            Runnable runnable = () -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            threadPool.execute(runnable);
        }
    }

}
