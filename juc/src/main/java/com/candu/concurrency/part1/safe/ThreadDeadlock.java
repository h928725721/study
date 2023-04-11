package com.candu.concurrency.part1.safe;

import java.util.concurrent.*;

public class ThreadDeadlock {
    ExecutorService exec = Executors.newSingleThreadExecutor();

    public class RenderPageTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            Future<String> header,footer;
            header = exec.submit(new LoadFileTask("header.html"));
            footer = exec.submit(new LoadFileTask("footer.html"));
            return header.get() + footer.get();
        }
    }







    private class LoadFileTask implements Callable<String> {
        public LoadFileTask(String s) {
        }


        @Override
        public String call() throws Exception {
            return null;
        }
    }
}
