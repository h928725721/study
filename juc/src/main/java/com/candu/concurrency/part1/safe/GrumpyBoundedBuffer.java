package com.candu.concurrency.part1.safe;

import org.apache.commons.collections.BufferOverflowException;
import org.apache.commons.collections.BufferUnderflowException;

/**
 * 当不满足前提条件时，有界缓存不会执行相应的操作
 * 先检查，再运行
 */
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V>{

    protected GrumpyBoundedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) {
        if (isFull()) {
            throw new BufferOverflowException();
        }
        doPUt(v);
    }
    public synchronized V take() {
        if (isEmpty()) throw new BufferUnderflowException();
        return doTake();
    }

    public void test() {
        GrumpyBoundedBuffer<V> buffer = new GrumpyBoundedBuffer<>(20);
        while (true) {
            try {
                V item = buffer.take();
                // 对于item执行一些操作
                break;
            } catch (BufferUnderflowException e){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                }
            }
        }
    }

}
