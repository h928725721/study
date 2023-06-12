package structural.proxy.schema.s3.proxy;

import cn.hutool.core.date.DateUtil;
import structural.proxy.schema.s3.base.GamePlayer;
import structural.proxy.schema.s3.base.IGamePlayer;

/**
 * 强制代理
 * 必须知道真实对象，并获取真实对象中的代理类，才能访问具体的方法
 *
 */
public class ProxyClient {
    public static void main(String[] args) {

//        errorAccess();
        proxyInPlayer();
    }

    /**
     * 使用真实对象中的代理类才能访问
     */
    private static void proxyInPlayer() {
        GamePlayer player = new GamePlayer("张三");
        IGamePlayer proxy = player.getProxy();
        System.out.println("the game start at : " + DateUtil.now());
        proxy.login("zhangsan","123456");
        proxy.killBoss();
        proxy.upgrade();
        System.out.println("the game end at : " + DateUtil.now());
    }

    /**
     * 当使用常规方法new 一个真实对象，会无法访问具体功能
     */
    private static void errorAccess() {
        GamePlayer player = new GamePlayer("张三");
        GamePlayerProxy proxy = new GamePlayerProxy(player);
        System.out.println("the game start at : " + DateUtil.now());
        proxy.login("zhangsan","123456");
        proxy.killBoss();
        proxy.upgrade();
        System.out.println("the game end at : " + DateUtil.now());
    }
}
