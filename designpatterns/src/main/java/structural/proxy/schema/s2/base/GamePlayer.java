package structural.proxy.schema.s2.base;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

public class GamePlayer implements IGamePlayer {
    String name = StringUtils.EMPTY;

    public GamePlayer(IGamePlayer gamePlayer,String _name) throws Exception {
        if (gamePlayer == null) {
            throw new Exception("不能创建真实角色！");
        }

        this.name = _name;
    }

    @Override
    public void login(String user, String password) {
        System.out.println("the login user is " + user + " ,and this user login success!");
    }

    @SneakyThrows
    @Override
    public void killBoss() {
        Thread.sleep(1000);
        System.out.println(this.name + " has killed the boss!");
    }

    @Override
    public void upgrade() {
        System.out.println(this.name + " has upgraded ");
    }
}
