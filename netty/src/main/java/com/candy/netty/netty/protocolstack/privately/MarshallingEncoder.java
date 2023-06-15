package com.candy.netty.netty.protocolstack.privately;

import com.candy.netty.netty.codingframe.marshalling.MarshallingCodeCFactory;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.OutputStreamByteOutput;

import java.io.IOException;

public class MarshallingEncoder {
    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];
    Marshaller marshalling;

    public MarshallingEncoder() throws IOException {
        this.marshalling = MarshallingCodeCFactory.buildMarshalling();
    }

    protected void encode(Object msg, ByteBuf out) throws IOException {
        try {
            int lengthPos = out.writerIndex();
            out.writeBytes(LENGTH_PLACEHOLDER);
            OutputStreamByteOutput output = new OutputStreamByteOutput(new ByteBufOutputStream(out));
            marshalling.start(output);
            marshalling.writeObject(msg);
            marshalling.finish();
            out.setInt(lengthPos, out.writerIndex() - lengthPos - 4);
        } finally {
            marshalling.close();
        }

    }
}
