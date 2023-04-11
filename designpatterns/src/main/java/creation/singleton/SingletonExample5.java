package creation.singleton;

/**
 * 懒汉模式（双重锁同步锁单例模式）
 * 线程安全
 */
public class SingletonExample5 {

    public SingletonExample5() {
    }

    private volatile static SingletonExample5 instance = null;

    public static SingletonExample5 getInstance() {
        if (instance == null) {
            synchronized (SingletonExample5.class) {
                if (instance == null) {
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
