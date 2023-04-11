package structural.proxy.staticproxy;

/**
 * 代理类的实现接口，完成委托类预处理消息、过滤消息、把消息转发给委托类，以及时候处理消息等
 * <p>
 * 学生代理类，实现person接口，保存一个学生实体，这样就可以代理学生产生行为
 */
public class StudentsProxy implements Person {

    Student student;

    public StudentsProxy(Person person) {
        this.student = (Student) person;
    }

    /**
     * 在执行被代理对象时，执行了一些其它的操作，这就是代理模式核心的作用
     */
    @Override
    public void giveMoney() {
        System.out.println("我来代理你交钱，先帮你数数钱够不够！");
        student.giveMoney();
    }
}
