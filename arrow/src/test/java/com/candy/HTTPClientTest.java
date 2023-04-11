package com.candy;

import com.candy.service.HTTPClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = App.class)
class HTTPClientTest {

    @Test
    void name() {
        String s = HTTPClient.testRequestJsonParse("/naive/{sname}", "http://lab.yhfund.com.cn:2022");

    }

}