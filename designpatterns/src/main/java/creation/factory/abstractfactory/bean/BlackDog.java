package creation.factory.abstractfactory.bean;

import creation.factory.abstractfactory.IDog;
import lombok.extern.slf4j.Slf4j;

public class BlackDog implements IDog {
    @Override
    public void eat() {
        System.out.println("The black dog is eating!");
    }
}
