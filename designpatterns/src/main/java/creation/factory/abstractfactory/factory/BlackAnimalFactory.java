package creation.factory.abstractfactory.factory;

import creation.factory.abstractfactory.bean.BlackCat;
import creation.factory.abstractfactory.bean.BlackDog;
import creation.factory.abstractfactory.ICat;
import creation.factory.abstractfactory.IDog;
import org.springframework.stereotype.Service;

/**
 * 抽象工厂的实现类
 */
@Service
public class BlackAnimalFactory implements IAnimalFactory{


    @Override
    public ICat createCat() {
        return new BlackCat();
    }

    @Override
    public IDog createDog() {
        return new BlackDog();
    }
}
