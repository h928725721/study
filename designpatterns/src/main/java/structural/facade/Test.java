package structural.facade;

public class Test {

    public static void main(String[] args) {
        /**
         * 模拟客户端原本调用子系统接口。只能一个一个调用，并且子系统修改后，客户端也要修改
         */
        ServiceA sa = new ServiceAImpl();
        ServiceB sb = new ServiceBImpl();
        sa.methodA();
        sb.methodB();
        System.out.println("========");
        /**
         * 模拟外观模式，封装后，客户端只需要调用外观接口，子系统修改后，只需要外观类中进行修改就行
         */
        Facade facade = new Facade();
        facade.facadeMethod();
    }
}
