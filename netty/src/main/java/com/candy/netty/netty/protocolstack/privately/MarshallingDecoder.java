package com.candy.netty.netty.protocolstack.privately;

import com.candy.netty.netty.codingframe.marshalling.MarshallingCodecFactory;
import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.Unmarshaller;

import java.io.IOException;

public class MarshallingDecoder {

    /**
     * 构建一个解码器
     */
    private final Unmarshaller provider;

    /**
     * Marshalling decoder
     *
     * @throws IOException io exception
     * @since 1.0
     */
    public MarshallingDecoder() throws IOException {
        this.provider = MarshallingCodecFactory.buildUnMarshalling();
    }

    /**
     * 执行数据解码
     *
     * @param byteBuf byte buf
     * @return object object
     * @throws IOException io exception
     * @since 1.0
     */
    public Object decode(ByteBuf byteBuf) throws IOException {
        //先读取对象的长度
        int objSize = byteBuf.readInt();
        //创建一个新的buf进行读取
        ByteBuf objBuf = byteBuf.slice(byteBuf.readerIndex(), objSize);
        ChannelBufferByteInput channelBufferByteInput = new ChannelBufferByteInput(objBuf);
        Object object = null;
        try {
            provider.start(channelBufferByteInput);
            object = provider.readObject();
            provider.finish();
            //将读的索引位置设置到当前读的索引位置+上对象长度的位置
            byteBuf.readerIndex(byteBuf.readerIndex() + objSize);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return object;
    }
}
