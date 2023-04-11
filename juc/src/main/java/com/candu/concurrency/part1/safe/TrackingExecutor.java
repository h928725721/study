package com.candu.concurrency.part1.safe;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class TrackingExecutor extends AbstractExecutorService {
    private final ExecutorService exec;
    private final Set<Runnable> tasksCancelledAtShutdown = Collections.synchronizedSet(new HashSet<>());
    public TrackingExecutor(ExecutorService exec) {
        this.exec = exec;
    }
    public List<Runnable> getCancelledTasks() {
        if (!exec.isTerminated()) {
            throw new IllegalStateException();
        }
        return new ArrayList<>(tasksCancelledAtShutdown);
    }
    @Override
    public void execute(final Runnable command) {
        exec.execute(() -> {
            try {
                command.run();
            } finally {
                if (isShutdown() && Thread.currentThread().isInterrupted()) {
                    tasksCancelledAtShutdown.add(command);
                }
            }
        });
    }
    @Override
    public void shutdown() {
        exec.shutdown();
    }
    @Override
    public List<Runnable> shutdownNow() {
        return exec.shutdownNow();
    }
    @Override
    public boolean isShutdown() {
        return exec.isShutdown();
    }
    @Override
    public boolean isTerminated() {
        return exec.isTerminated();
    }
    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return exec.awaitTermination(timeout,unit);
    }
}
