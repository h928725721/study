package creation.factory.explan;

public class WhiteHuman implements Human{
    @Override
    public void getColor() {
        System.out.println("白人都是猩猩！");
    }

    @Override
    public void talk() {
        System.out.println("white bad");
    }
}
