package com.candu.concurrency.part1.safe;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadPerTaskWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(80);
        while (true){
            final Socket accept = serverSocket.accept();
            Runnable task = () -> handleRequest(accept);
            new Thread(task).start();
        }
    }

    private static void handleRequest(Socket accept) {
        //do something
    }
}
