package com.candu.concurrency.part1.safe;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ReaderThread extends Thread{
    private final Socket socket;
    private final InputStream in;
    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    /**
     * 重写了interrupt,使其既能处理标准的中断,又能关闭底层套接字
     */
    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException e) {
        } finally {
            super.interrupt();
        }
    }
    @Override
    public void run() {
        try {
            byte[] buf = new byte[30];
            while (true) {
                int count = in.read(buf);
                if (count < 0) {
                    break;
                } else if (count > 0) {
                    //do something
                }
            }
        } catch (IOException e) {
            //允许线程退出
        }
    }
}
