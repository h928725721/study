package creation.singleton;

/**
 * 懒汉模式，第一次使用时创建，线程安全但不推荐
 */
public class SingletonExample3 {

    public SingletonExample3() {
    }

    private static SingletonExample3 instance = null;

    public static synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
