package com.candu.concurrency.part1.safe;

public class Widget {
    public synchronized void doSomething() {
        //do something
    }
}

class LoggingWidget extends Widget {
    @Override
    public synchronized void doSomething() {
        System.out.println(toString() + ": calling doSomething");
        super.doSomething();
    }
}
