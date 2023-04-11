package structural.proxy.dynamicproxy;

import lombok.SneakyThrows;

/**
 * 被代理对象实现接口，实现具体的业务逻辑
 */
public class Student implements Person {

    private final String name;

    public Student(String name) {
        this.name = name;
    }

    @SneakyThrows
    @Override
    public void giveMoney() {
        Thread.sleep(1000);
        System.out.println(name + "上交班费50元");
    }
}
