package creation.singleton;

/**
 * 懒汉模式，单例仅在第一次使用时创建
 */
public class SingletonExample1 {

    public SingletonExample1() {
    }

    private static SingletonExample1 instance = null;

    public static SingletonExample1 getInstance() {
        //线程不安全，多线程调用可能创建多个对象
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
