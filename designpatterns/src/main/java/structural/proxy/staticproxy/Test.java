package structural.proxy.staticproxy;

public class Test {

    /**
     * 静态代理，就是代码都是写好的，在编译时就已经将接口、被代理类和代理类等确定下来，在程序运行之前，代理类的.class文件就已经生成
     */
    public static void main(String[] args) {
        //被代理人张三，他上交学费的行为交给代理类来完成
        Person student = new Student("张三");
        Person monitor = new StudentsProxy(student);
        monitor.giveMoney();
    }

}
