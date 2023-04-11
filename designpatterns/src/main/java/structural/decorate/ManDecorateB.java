package structural.decorate;

public class ManDecorateB extends Decorator{

    @Override
    public void eat() {
        super.eat();
        System.out.println("============");
        System.out.println("ManDecorateB类");
    }
}
