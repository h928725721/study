package structural.decorate;

public class ManDecorateA extends Decorator{

    @Override
    public void eat() {
        super.eat();
        reEat();
        System.out.println("ManDecorateA类");
    }

    public void reEat() {
        System.out.println("再吃一顿饭！");
    }


}
