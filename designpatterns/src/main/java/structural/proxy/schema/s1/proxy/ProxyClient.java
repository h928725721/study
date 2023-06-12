package structural.proxy.schema.s1.proxy;

import cn.hutool.core.date.DateUtil;
import structural.proxy.schema.s1.base.GamePlayer;

/**
 * 通过将被代理对象通过构造函数传递给代理类，达到使用代理类完成被代理对象的功能
 */
public class ProxyClient {
    public static void main(String[] args) {
        GamePlayer player = new GamePlayer("张三");
        GamePlayerProxy proxy = new GamePlayerProxy(player);
        System.out.println("the game start at : " + DateUtil.now());
        proxy.login("zhangsan","123456");
        proxy.killBoss();
        proxy.upgrade();
        System.out.println("the game end at : " + DateUtil.now());
    }
}
