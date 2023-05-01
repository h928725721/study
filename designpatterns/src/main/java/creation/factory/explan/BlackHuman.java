package creation.factory.explan;

public class BlackHuman implements Human{
    @Override
    public void getColor() {
        System.out.println("黑人都是尼格！");
    }

    @Override
    public void talk() {
        System.out.println("black's life live");
    }
}
