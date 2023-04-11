package creation.singleton;

/**
 * 饿汉模式，线程安全
 */
public class SingletonExample6 {

    public SingletonExample6() {
    }

    private static final SingletonExample6 INSTANCE;

    static {
        INSTANCE = new SingletonExample6();
    }

    public static SingletonExample6 getInstance() {
        return INSTANCE;
    }
}
