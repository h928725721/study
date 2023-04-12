package com.candy.netty.bio.fakeasync;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServerHandlerExecutePool {

    private final ExecutorService executor;

    public TimeServerHandlerExecutePool(int maxSize, int queueSize) {
        this.executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxSize, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize));
    }

    public void execute(Runnable task) {
        executor.execute(task);
    }
}
