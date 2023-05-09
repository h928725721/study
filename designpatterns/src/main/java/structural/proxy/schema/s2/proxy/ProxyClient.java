package structural.proxy.schema.s2.proxy;

import cn.hutool.core.date.DateUtil;
import structural.proxy.schema.s2.base.GamePlayer;

/**
 * 普通代理
 * 通过改变GamePlayer 和 proxy类的构造函数，使得上层使用时无需关心真是角色是谁，只要知道代理类即可
 * 很适合对拓展性要求较高的场合，实际中一般要通过约定来禁止new一个真实角色
 */
public class ProxyClient {
    public static void main(String[] args) throws Exception {
        GamePlayerProxy proxy = new GamePlayerProxy("张三");
        System.out.println("the game start at : " + DateUtil.now());
        proxy.login("zhangsan","123456");
        proxy.killBoss();
        proxy.upgrade();
        System.out.println("the game end at : " + DateUtil.now());
    }
}
