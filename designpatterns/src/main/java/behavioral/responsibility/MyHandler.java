package behavioral.responsibility;

public abstract class MyHandler {

    protected MyHandler next;

    public void next(MyHandler next) {
        this.next = next;
    }
    public abstract void doHandler(LoginUser user);

}
