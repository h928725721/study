package com.candu.concurrency.part1.safe;

import javax.annotation.concurrent.GuardedBy;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class HiddenIterator {
    @GuardedBy("this")
    private final Set<Integer> set = new HashSet<>();

    private ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

    public synchronized void add(Integer i) {set.add(i);}
    public synchronized void remove(Integer i) {set.remove(i);}

    public void addTenThings() {
        map.put("1",121212);
        Random r = new Random();
        for (int i = 0;i < 10; i++) {
            add(r.nextInt());
        }
        System.out.println("DEBUG: added ten elements to " + set);
    }
}
