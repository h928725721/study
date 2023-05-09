package structural.proxy.dynamicproxy.schema.proxy;

import cn.hutool.core.date.DateUtil;
import structural.proxy.dynamicproxy.schema.base.GamePlayer;
import structural.proxy.dynamicproxy.schema.base.IGamePlayer;

import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 不需要创建代理类，也没有实现IGamePlayer接口
 * 并且还可以在游戏登录时给我们发送登录消息
 */
public class ProxyClient {
    public static void main(String[] args) {
        GamePlayer player = new GamePlayer("张三");
        GamePlayIH handler = new GamePlayIH(player);
        System.out.println("the game start at : " + DateUtil.now());
        ClassLoader cl = player.getClass().getClassLoader();
        //动态产生一个代理者
        IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(cl, new Class[]{IGamePlayer.class}, handler);
        proxy.login("zhangsan","123456");
        proxy.killBoss();
        proxy.upgrade();
        System.out.println("the game end at : " + DateUtil.now());
    }
}
