package com.candu.concurrency.part1.safe;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class OneShotLatch {
    private final Sync sync = new Sync();
    private final Semaphore semaphore = new Semaphore(1);
    public void signal() {
        /**
         *     public final boolean releaseShared(int arg) {
         *         if (tryReleaseShared(arg)) {
         *             doReleaseShared();
         *             return true;
         *         }
         *         return false;
         *     }
         */
        sync.releaseShared(0);
    }
    public void await() throws InterruptedException {
        /**
         *     接着会执行Sync中的tryAcquireShared
         *     public final void acquireSharedInterruptibly(int arg)
         *             throws InterruptedException {
         *         if (Thread.interrupted())
         *             throw new InterruptedException();
         *         if (tryAcquireShared(arg) < 0)
         *             doAcquireSharedInterruptibly(arg);
         *     }
         *     该方法处理失败的方式是吧这个线程放入等待线程队列中
         */
        sync.acquireSharedInterruptibly(0);
    }

    private class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int arg) {
            //如果闭锁是开的，那么这个操作将成功，否则失败
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            //打开闭锁
            setState(1);
            //其他线程可以获得该闭锁
            return true;
        }
    }
}
