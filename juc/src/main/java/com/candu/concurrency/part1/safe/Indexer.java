package com.candu.concurrency.part1.safe;


import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Indexer implements Runnable {

    private final BlockingQueue<File> queue;

    public Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                indexFile(queue.take());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void indexFile(File take) {

    }


    public static void main(String[] args) {

    }

    public static void startIndexing(File[] roots) {
        LinkedBlockingQueue<File> queue = new LinkedBlockingQueue<>(20);
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        };
        for (File root : roots) {
            new Thread(new FileCrawler(queue,filter,root)).start();
        }
        for (int i = 0; i < 20; i++) {
            new Thread(new Indexer(queue)).start();
        }
    }
}




