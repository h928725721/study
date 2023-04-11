package creation.factory.abstractfactory.bean;

import creation.factory.abstractfactory.IDog;
import lombok.extern.slf4j.Slf4j;

public class WhiteDog implements IDog {
    @Override
    public void eat() {
        System.out.println("The white dog is eating!");
    }
}
