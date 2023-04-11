package creation.factory.abstractfactory.factory;

import creation.factory.abstractfactory.ICat;
import creation.factory.abstractfactory.IDog;
import creation.factory.abstractfactory.bean.WhiteCat;
import creation.factory.abstractfactory.bean.WhiteDog;
import org.springframework.stereotype.Service;

/**
 * 抽象工厂的实现类
 */
@Service
public class WhiteAnimalFactory implements IAnimalFactory{


    @Override
    public ICat createCat() {
        return new WhiteCat();
    }

    @Override
    public IDog createDog() {
        return new WhiteDog();
    }
}
