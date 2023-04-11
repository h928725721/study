package com.candu.concurrency.part1.safe;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

public class PersonSet {

    private final Set<Person> mySet = new HashSet<>();

    public synchronized void addPerson(Person person) {
        mySet.add(person);
    }

    public synchronized boolean containsPerson(Person p) {
        return mySet.contains(p);
    }

    @Data
    static class Person {
        private String name;
        private String age;
    }
}
