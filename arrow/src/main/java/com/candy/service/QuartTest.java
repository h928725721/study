package com.candy.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
@EnableScheduling
public class QuartTest {


    @Scheduled(cron = "0 58 9 * * ?")
    public void test() {
        System.out.println("test success");
        System.out.println("-----------------------------------");
        ConcurrentHashMap<Object, Object> a = new ConcurrentHashMap<>();
        a.put(1,1);
    }

}
