package structural.proxy.dynamicproxy;

import java.lang.reflect.Proxy;

public class Test {
    /**
     * 代理类在程序运行时动态创建的成为动态代理
     * 动态代理可以很方便的对代理类的函数进行统一处理而不用修改每个代理类中的方法
     *
     * 本例中，在代理方法上执行操作时，不需要修改很多代码就可以实现，相比静态代理更加灵活，如果代码变动只需要修改代理类中的逻辑就可以
     *
     * @param args
     */
    public static void main(String[] args) {
        //被代理对象张三
        Person zhangsan = new Student("张三");
        //创建一个与代理对象相关联的 InvocationHandler
        StuInvocationHandler<Person> invocationHandler = new StuInvocationHandler<>(zhangsan);
        //创建一个代理对象来代理张三
        Person instance = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, invocationHandler);

        instance.giveMoney();

    }
}
