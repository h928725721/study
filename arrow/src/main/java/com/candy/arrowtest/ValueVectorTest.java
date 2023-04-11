package com.candy.arrowtest;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.RootAllocator;
import org.apache.arrow.vector.BitVector;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.VarCharVector;
import org.apache.arrow.vector.VectorSchemaRoot;
import org.apache.arrow.vector.ipc.ArrowStreamReader;
import org.apache.arrow.vector.ipc.ArrowStreamWriter;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.Schema;
import org.apache.arrow.vector.util.Text;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class ValueVectorTest {
    static BufferAllocator allocator = new RootAllocator();


    public static void main(String[] args) {
        getSchema();
    }


    @SneakyThrows
    public static Schema getSchema() {
        ByteArrayOutputStream out = writeStream();
        readStream(out);
        return null;
    }

    private static void readStream(ByteArrayOutputStream s) throws IOException {
        try (ArrowStreamReader reader = new ArrowStreamReader(new ByteArrayInputStream(s.toByteArray()), allocator)) {
            VectorSchemaRoot schemaRoot = reader.getVectorSchemaRoot();
            reader.loadNextBatch();
            List<FieldVector> fieldVectors = schemaRoot.getFieldVectors();
            long start = System.currentTimeMillis();
            fieldVectors.forEach(fieldVector -> {
                long start1 = System.currentTimeMillis();
                typeHandler(fieldVector);
                long end1 = System.currentTimeMillis();
                log.info("each time -------------------------> {}",end1 - start1);
            });
            long end = System.currentTimeMillis();
            log.info("total time -------------------------> {}",end - start);
        }
    }

    private static void typeHandler(FieldVector fieldVector) {
        Types.MinorType minorType = fieldVector.getMinorType();
        switch (minorType) {
            case BIT:
                BitVector b = (BitVector) fieldVector;
                int bCount = b.getValueCount();
                for (int i = 0; i < bCount; i++) {
                    Boolean i1 = b.getObject(i);
                    //do something
                }
                break;
            case VARCHAR:
                VarCharVector v = (VarCharVector) fieldVector;
                int vCount = v.getValueCount();
                for (int i = 0; i < vCount; i++) {
                    Text bytes = v.getObject(i);
                    //do something
                }
                break;
        }

    }

    private static ByteArrayOutputStream writeStream() throws IOException {
        BitVector bitVector = new BitVector("boolean", allocator);
        VarCharVector varCharVector = new VarCharVector("varchar", allocator);
        for (int i = 0; i < 100000000; i++) {
            bitVector.setSafe(i, i % 2 == 0 ? 0 : 1);
            varCharVector.setSafe(i, ("test" + i).getBytes(StandardCharsets.UTF_8));
        }
        bitVector.setValueCount(100000000);
        varCharVector.setValueCount(100000000);

        List<Field> fields = Arrays.asList(bitVector.getField(), varCharVector.getField());
        List<FieldVector> vectors = Arrays.asList(bitVector, varCharVector);
        VectorSchemaRoot root = new VectorSchemaRoot(fields, vectors);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ArrowStreamWriter writer = new ArrowStreamWriter(root, /*DictionaryProvider=*/null, Channels.newChannel(out));
        writer.start();
        writer.writeBatch();

        for (int i = 0; i < 4; i++) {
            BitVector childVector1 = (BitVector) root.getVector(0);
            VarCharVector childVector2 = (VarCharVector) root.getVector(1);
            childVector1.reset();
            childVector2.reset();
            writer.writeBatch();
        }

        writer.end();
        return out;
    }


}
