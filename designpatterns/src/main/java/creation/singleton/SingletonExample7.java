package creation.singleton;

/**
 * 枚举方式实例化，线程安全
 */
public class SingletonExample7 {

    public SingletonExample7() {
    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getSingleton();
    }


    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;
        //jvm保证这个方法只会被执行1次
        Singleton () {
            singleton = new SingletonExample7();
        }
        public SingletonExample7 getSingleton() {
            return singleton;
        }
    }
}
