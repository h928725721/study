package com.candu.concurrency.part1.safe;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

class PrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;

    PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                queue.put(p = p.nextProbablePrime());
            } catch (InterruptedException e) {
                //允许线程退出
            }
        }
    }
}