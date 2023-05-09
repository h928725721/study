package structural.proxy.dynamicproxy.schema.base;

import cn.hutool.core.date.DateUtil;

public class Client {
    public static void main(String[] args) {
        GamePlayer player = new GamePlayer("张三");
        System.out.println("the game start at : " + DateUtil.now());
        player.login("zhangsan","123456");
        player.killBoss();
        player.upgrade();
        System.out.println("the game end at : " + DateUtil.now());
    }
}
