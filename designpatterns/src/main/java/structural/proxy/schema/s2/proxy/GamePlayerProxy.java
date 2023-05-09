package structural.proxy.schema.s2.proxy;

import structural.proxy.schema.s2.base.GamePlayer;
import structural.proxy.schema.s2.base.IGamePlayer;

public class GamePlayerProxy implements IGamePlayer {
    //被代理对象
    private IGamePlayer gamePlayer = null;

    public GamePlayerProxy(String name) {
        try {
            gamePlayer = new GamePlayer(this,name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void login(String user, String password) {
        this.gamePlayer.login(user,password);
    }

    @Override
    public void killBoss() {
        this.gamePlayer.killBoss();
    }

    @Override
    public void upgrade() {
        this.gamePlayer.upgrade();
    }
}
