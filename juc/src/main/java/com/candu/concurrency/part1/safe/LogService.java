package com.candu.concurrency.part1.safe;

import java.io.PrintWriter;
import java.util.concurrent.*;

public class LogService {
    private final ExecutorService exec = Executors.newSingleThreadExecutor();
    private final PrintWriter writer;
    public LogService(PrintWriter writer) {
        this.writer = writer;
    }
    public void stop() {
        try {
            exec.shutdown();
            exec.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {

        }finally {
            writer.close();
        }
    }
}
