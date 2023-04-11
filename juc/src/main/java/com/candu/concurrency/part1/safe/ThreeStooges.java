package com.candu.concurrency.part1.safe;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;

@Immutable
public final class ThreeStooges {
    private final Set<String> stooges = new HashSet<>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }
}
