package structural.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StuInvocationHandler<T> implements InvocationHandler {

    T target;

    public StuInvocationHandler(T target) {
        this.target = target;
    }

    /**
     * 所有执行代理对象的方法都会被替换成执行invoke方法
     *
     * @param proxy 代表动态代理对象
     * @param method 代表正在执行的方法
     * @param args 代表调用目标方法时传入的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理执行" + method.getName() + "方法");
        //插入监测方法，计算该方法耗时
        MonitorUtil.start();
        Object result = method.invoke(target, args);
        MonitorUtil.finish(method.getName());
        return result;
    }
}
