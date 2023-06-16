package com.candy.netty.netty.protocolstack.privately;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteOutput;

import java.io.IOException;

/**
 * 封装的缓冲输入流，用于向buffer中写入数据
 */
public class ChannelBufferByteOutput implements ByteOutput {

    /** Buffer */
    private final ByteBuf buffer;

    /**
     * Create a new instance which use the given {@link ByteBuf}
     *
     * @param buffer buffer
     * @since 1.0
     */
    public ChannelBufferByteOutput(ByteBuf buffer) {
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
        // Nothing to do
    }

    /**
     * Flush
     *
     * @throws IOException io exception
     * @since 1.0
     */
    @Override
    public void flush() throws IOException {
        // nothing to do
    }

    /**
     * Write
     *
     * @param b b
     * @throws IOException io exception
     * @since 1.0
     */
    @Override
    public void write(int b) throws IOException {
        buffer.writeByte(b);
    }

    /**
     * Write
     *
     * @param bytes bytes
     * @throws IOException io exception
     * @since 1.0
     */
    @Override
    public void write(byte[] bytes) throws IOException {
        buffer.writeBytes(bytes);
    }

    /**
     * Write
     *
     * @param bytes    bytes
     * @param srcIndex src index
     * @param length   length
     * @throws IOException io exception
     * @since 1.0
     */
    @Override
    public void write(byte[] bytes, int srcIndex, int length) throws IOException {
        buffer.writeBytes(bytes, srcIndex, length);
    }

    /**
     * Return the {@link ByteBuf} which contains the written content
     *
     * @return the buffer
     * @since 1.0
     */
    ByteBuf getBuffer() {
        return buffer;
    }
}
