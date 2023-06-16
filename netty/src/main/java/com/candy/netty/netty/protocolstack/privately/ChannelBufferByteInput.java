package com.candy.netty.netty.protocolstack.privately;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteInput;

import java.io.IOException;

/**
 * 封装一个字节输入流，用于 Marshall 读取buffer中对象数据
 */
public class ChannelBufferByteInput implements ByteInput {

    /** Buffer */
    private final ByteBuf buffer;

    /**
     * Channel buffer byte input
     *
     * @param buffer buffer
     * @since 1.0
     */
    public ChannelBufferByteInput(ByteBuf buffer) {
        this.buffer = buffer;
    }

    /**
     * Close
     *
     * @throws IOException io exception
     * @since 1.0
     */
    @Override
    public void close() throws IOException {
        // nothing to do
    }

    /**
     * Available
     *
     * @return the int
     * @throws IOException io exception
     * @since 1.0
     */
    @Override
    public int available() throws IOException {
        return buffer.readableBytes();
    }

    /**
     * Read
     *
     * @return the int
     * @throws IOException io exception
     * @since 1.0
     */
    @Override
    public int read() throws IOException {
        if (buffer.isReadable()) {
            return buffer.readByte() & 0xff;
        }
        return -1;
    }

    /**
     * Read
     *
     * @param array array
     * @return the int
     * @throws IOException io exception
     * @since 1.0
     */
    @Override
    public int read(byte[] array) throws IOException {
        return read(array, 0, array.length);
    }

    /**
     * Read
     *
     * @param dst      dst
     * @param dstIndex dst index
     * @param length   length
     * @return the int
     * @throws IOException io exception
     * @since 1.0
     */
    @Override
    public int read(byte[] dst, int dstIndex, int length) throws IOException {
        int available = available();
        if (available == 0) {
            return -1;
        }

        length = Math.min(available, length);
        buffer.readBytes(dst, dstIndex, length);
        return length;
    }

    /**
     * Skip
     *
     * @param bytes bytes
     * @return the long
     * @throws IOException io exception
     * @since 1.0
     */
    @Override
    public long skip(long bytes) throws IOException {
        int readable = buffer.readableBytes();
        if (readable < bytes) {
            bytes = readable;
        }
        buffer.readerIndex((int) (buffer.readerIndex() + bytes));
        return bytes;
    }

}
