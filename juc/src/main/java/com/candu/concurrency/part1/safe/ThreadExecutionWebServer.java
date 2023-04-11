package com.candu.concurrency.part1.safe;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadExecutionWebServer {
    private static final int NTHREADS = 100;
    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(80);
        while (true){
            final Socket accept = serverSocket.accept();
            Runnable task = () -> handleRequest(accept);
            exec.execute(task);
        }
    }
    private static void handleRequest(Socket accept) {
        //do something
    }
}
