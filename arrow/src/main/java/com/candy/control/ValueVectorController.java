package com.candy.control;

import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.types.pojo.Schema;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;

@RestController
public class ValueVectorController {


    @PostMapping("/test")
    public Schema getSchema() {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("K1", "V1");
        metadata.put("K2", "V2");
        Field a = new Field("A", FieldType.nullable(new ArrowType.Int(32, true)), /*children*/ null);
        Field b = new Field("B", FieldType.nullable(new ArrowType.Utf8()), /*children*/ null);
        Schema schema = new Schema(asList(a, b), metadata);
        System.out.println("Schema created: " + schema);
        return schema;
    }


}
