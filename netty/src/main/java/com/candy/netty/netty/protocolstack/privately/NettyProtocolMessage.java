package com.candy.netty.netty.protocolstack.privately;

import io.netty.buffer.ByteBuf;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * netty协议消息体
 */
@Data
@ToString
@Builder
public final class NettyProtocolMessage {
    //头部数据
    private Header header;
    //消息体
    private Object body;

    /**
     * 创建Message对象
     *
     * @param byteBuf
     * @param decoder
     * @return
     */
    public static NettyProtocolMessage createMessage(ByteBuf byteBuf, MarshallingDecoder decoder) throws IOException {
        //读取消息头部，按照指定直接的长度
        int crcCode = byteBuf.readInt();
        int length = byteBuf.readInt();
        long sessionId = byteBuf.readLong();
        byte type = byteBuf.readByte();
        byte priority = byteBuf.readByte();

        //读取附件数据
        int size = byteBuf.readInt();
        Map<String, Object> attach = null;
        if (size > 0) {
            attach = new HashMap<>(size);
            for (int i = 0; i < size; i++) {
                //先获取到 key数据的长度
                int keySize = byteBuf.readInt();
                //创建一个key长度的字节数据
                byte[] keyArrays = new byte[keySize];
                //读取数据
                byteBuf.readBytes(keyArrays);
                //转换成string字符串形式
                String key = new String(keyArrays, StandardCharsets.UTF_8);
                //读取value数据
                attach.put(key, decoder.decode(byteBuf));
            }
        }
        Object body = null;
        //如果剩下未读的数据长度大于4个字节，证明有body的数据，进行解码
        if (byteBuf.readableBytes() > 4) {
            body = decoder.decode(byteBuf);
        }
        Header header = Header.builder()
            .crcCode(crcCode)
            .length(length)
            .sessionID(sessionId)
            .type(type)
            .priority(priority)
            .attachment(attach).build();
        return NettyProtocolMessage.builder().header(header).body(body).build();
    }
}
