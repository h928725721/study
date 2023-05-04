package creation.factory.explan;

public class Test {
    public static void main(String[] args) {
        YellowHuman human = HumanFactory.createHuman(YellowHuman.class);
        human.getColor();
        human.talk();
    }
}
