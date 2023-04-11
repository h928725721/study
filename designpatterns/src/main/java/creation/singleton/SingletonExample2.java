package creation.singleton;

/**
 * 饿汉模式 类装载时创建，线程安全
 */
public class SingletonExample2 {

    public SingletonExample2() {
    }

    private static SingletonExample2 instance = new SingletonExample2();

    public static SingletonExample2 getInstance() {
        return instance;
    }
}
