package structural.proxy.schema.s1.base;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

public class GamePlayer implements IGamePlayer {
    String name = StringUtils.EMPTY;

    public GamePlayer(String _name) {
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
