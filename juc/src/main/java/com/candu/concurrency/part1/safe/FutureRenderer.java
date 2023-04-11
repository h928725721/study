package com.candu.concurrency.part1.safe;

import com.sun.scenario.effect.ImageData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureRenderer {

    private static final ExecutorService executor = Executors.newFixedThreadPool(100);
    public static void timedRun( Runnable r,long timeout,TimeUnit unit) throws InterruptedException, ExecutionException {
        Future<?> task = executor.submit(r);
        try {
            task.get(timeout,unit);
        } catch (ExecutionException e) {
            throw e;
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            task.cancel(true);
        }
    }
}
