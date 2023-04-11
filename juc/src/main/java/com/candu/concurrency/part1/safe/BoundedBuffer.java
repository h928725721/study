package com.candu.concurrency.part1.safe;

import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer <V> extends BaseBoundedBuffer<V>{
    protected BoundedBuffer(int capacity) {
        super(capacity);
    }
    //条件谓词： not-full (!isFull())
    //条件谓词： not-empty(!isEmpty())

    //阻塞，并直到： not-full
    public synchronized void put(V v) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        boolean wasEmpty = isEmpty();
        doPUt(v);
        if (wasEmpty) notifyAll();
    }
    //阻塞，并直到： not-empty
    public synchronized V take() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        V v = doTake();
        notifyAll();
        return v;
    }
}
