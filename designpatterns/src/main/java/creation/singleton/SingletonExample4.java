package creation.singleton;

/**
 * 懒汉模式，双重锁，但仍是线程不安全
 */
public class SingletonExample4 {

    public SingletonExample4() {
    }

    private static SingletonExample4 instance = null;


    /**
     * 虽然用了双重判断和同步，但仍然线程不安全，分析如下：
     * 当执行 instance = new SingletonExample4()时，CPU 会执行以下指令：
     * 1.memory = allocate() 分配对象内存空间
     * 2.ctorInstance 初始化对象
     * 3.instance = memory 设置instance指向刚分配的内存
     * 单线程执行上述步骤时不会有问题，但是多线程时，JVM和CPU优化会发生指令重排序，打乱上述三个步骤的执行
     * 1.memory = allocate() 分配对象内存空间
     * 2.instance = memory 设置instance指向刚分配的内存
     * 3.ctorInstance 初始化对象
     * 假设有两个线程A和B同时执行getInstance()方法，A执行到 instance = new SingletonExample4() 处，B刚执行到第一个 if (instance == null) 处，假设
     * A 线程执行 3.instance = memory 设置instance指向刚分配的内存，此时，B判断instance已有值，就会直接人会instance，而实际上线程A还未执行2.ctorInstance
     * 初始化对象，也就是B拿到的instance对象还未进行初始化，这个未初始化的对象一旦被线程B使用，就会出现问题。
     */
    public static SingletonExample4 getInstance() {
        if (instance == null) {
            synchronized (SingletonExample4.class) {
                if (instance == null) {
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }


}
