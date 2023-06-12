package structural.proxy.schema.s3.base;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import structural.proxy.schema.s3.proxy.GamePlayerProxy;

/**
 * 增加了一个代理类，访问真实对象的功能必须通过代理类
 */
public class GamePlayer implements IGamePlayer {
    String name;

    private IGamePlayer proxy = null;

    public GamePlayer(String _name) {
        this.name = _name;
    }

    @Override
    public void login(String user, String password) {
        if (isProxy()) {
            System.out.println("the login user is " + user + " ,and this user login success!");
        } else {
            System.out.println("please use the proxy class to access");
        }
    }

    @SneakyThrows
    @Override
    public void killBoss() {
        if (isProxy()) {
            Thread.sleep(1000);
            System.out.println(this.name + " has killed the boss!");
        } else {
            System.out.println("please use the proxy class to access");
        }
    }

    @Override
    public void upgrade() {
        if (isProxy()) System.out.println(this.name + " has upgraded ");
        else System.out.println("please use the proxy class to access");
    }

    @Override
    public IGamePlayer getProxy() {
        this.proxy = new GamePlayerProxy(this);
        return this.proxy;
    }

    /**
     * 检查是否是代理访问
     *
     * @return
     */
    private boolean isProxy() {
        if (this.proxy == null) {
            return false;
        }
        return true;
    }
}
