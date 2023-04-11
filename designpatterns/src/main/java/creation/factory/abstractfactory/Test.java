package creation.factory.abstractfactory;

import creation.factory.abstractfactory.factory.BlackAnimalFactory;
import creation.factory.abstractfactory.factory.IAnimalFactory;

public class Test {


    public static void main(String[] args) {
        IAnimalFactory blackAnimalFactory = new BlackAnimalFactory();
        ICat blackCat = blackAnimalFactory.createCat();
        blackCat.eat();
        IDog blackDog = blackAnimalFactory.createDog();
        blackDog.eat();
    }


}
