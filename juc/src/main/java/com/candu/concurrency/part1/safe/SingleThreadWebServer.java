package com.candu.concurrency.part1.safe;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(80);
        while (true){
            Socket accept = serverSocket.accept();
            handleRequest(accept);
        }
    }

    private static void handleRequest(Socket accept) {
        //do something
    }
}
