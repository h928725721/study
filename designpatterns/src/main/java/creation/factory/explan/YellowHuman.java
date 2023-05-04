package creation.factory.explan;

public class YellowHuman implements Human{
    @Override
    public void getColor() {
        System.out.println("我们都是炎黄子孙！");
    }

    @Override
    public void talk() {
        System.out.println("yellow is the future god");
    }
}
