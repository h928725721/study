package creation.factory.abstractfactory.bean;

import creation.factory.abstractfactory.ICat;
import lombok.extern.slf4j.Slf4j;

public class BlackCat implements ICat {
    @Override
    public void eat() {
        System.out.println("The black cat is eating!");
    }
}
