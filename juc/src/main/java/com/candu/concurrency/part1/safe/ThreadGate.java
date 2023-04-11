package com.candu.concurrency.part1.safe;

public class ThreadGate {
    //条件谓词： opened-since(n) (isOpen || generation>n)
    private boolean isOpen;
    private int generation;
    public synchronized void close() {
        isOpen = false;
    }
    public synchronized void open() {
        ++generation;
        isOpen = true;
        notifyAll();
    }
    //阻塞并直到：opened-since(generation on entity)
    public synchronized void await() throws InterruptedException {
        int arrivalGeneration = generation;
        /**
         * 当阀门打开时，有N个线程正在等待它，那么这些线程都应该被允许执行。
         * 然而如果阀门在打开后又非常快速的关闭了，并且await方法只检查isOpen，那么所有的线程可能都无法释放：当所有线程收到通知时
         * 将重新请求锁并推出wait，而此时阀门可能已经再次关闭了。
         * 每次阀门关闭时，递增一个“Generation”计数器，如果阀门现在是打开的，或者阀门自从该线程到达后就一直是打开的，那么线程就可以通过await。
         */
        while (!isOpen && arrivalGeneration == generation) {
            wait();
        }
    }
}
