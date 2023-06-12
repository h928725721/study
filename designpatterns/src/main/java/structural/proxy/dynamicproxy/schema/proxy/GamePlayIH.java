package structural.proxy.dynamicproxy.schema.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class GamePlayIH implements InvocationHandler {

    /**
     * 被代理者
     */
    Class cls = null;

    /**
     * 被代理的实例
     */
    Object obj = null;

    public GamePlayIH(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(this.obj, args);
        if (method.getName().equals("login")) System.out.println("someone has login your account! ");
        return result;
    }
}
