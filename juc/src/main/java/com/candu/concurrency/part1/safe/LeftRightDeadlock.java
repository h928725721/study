package com.candu.concurrency.part1.safe;

import java.util.concurrent.locks.ReentrantLock;

public class LeftRightDeadlock {
    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() {
        synchronized (left) {
            synchronized (right) {
                //doSomething
            }
        }
    }

    public void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                //doSomething
            }
        }
    }


    private static final Object tieLock = new Object();
    public void transferMoney(Account fromAccount, Account toAccount) {
        class Helper {
            public void transfer() {
                //do something
            }
        }
        int fromHash = System.identityHashCode(fromAccount);
        int toHash = System.identityHashCode(toAccount);
        if (fromHash < toHash) {
            synchronized (fromAccount) {
                synchronized (toAccount) {
                    new Helper().transfer();
                }
            }
        } else if (toHash < fromHash) {
            synchronized (toAccount) {
                synchronized (fromAccount) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (fromAccount) {
                    synchronized (toAccount) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }



    ReentrantLock lock = new ReentrantLock();
    public void sendOnSharedLine(String message) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            //do something
        } finally {
            lock.unlock();
        }
    }


}
