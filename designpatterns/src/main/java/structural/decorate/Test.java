package structural.decorate;

public class Test {


    public static void main(String[] args) {
        Man man = new Man();
        Decorator manDecorateA = new ManDecorateA();
        Decorator manDecorateB = new ManDecorateB();

        manDecorateA.setPerson(man);
        manDecorateB.setPerson(manDecorateA);
        manDecorateB.eat();

    }

}
