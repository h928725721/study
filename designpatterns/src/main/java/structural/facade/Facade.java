package structural.facade;

public class Facade {

    ServiceA sa;

    ServiceB sb;

    ServiceC sc;

    public Facade() {
        this.sa = new ServiceAImpl();
        this.sb = new ServiceBImpl();
        this.sc = new ServiceCImpl();
    }

    public void facadeMethod() {
        sa.methodA();
        sb.methodB();
        sc.methodC();
    }


}
