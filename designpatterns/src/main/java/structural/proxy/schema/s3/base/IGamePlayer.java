package structural.proxy.schema.s3.base;

public interface IGamePlayer {
    void login(String user,String password);

    void killBoss();

    void upgrade();

    IGamePlayer getProxy();






}
