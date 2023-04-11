package creation.factory.abstractfactory.factory;

import creation.factory.abstractfactory.ICat;
import creation.factory.abstractfactory.IDog;

/**
 * 抽象工厂
 */
public interface IAnimalFactory {

    /**
     * 定义创建ICat接口实例的方法
     */
    ICat createCat();
    /**
     * 定义创建ICat接口实例的方法
     */
    IDog createDog();

}
