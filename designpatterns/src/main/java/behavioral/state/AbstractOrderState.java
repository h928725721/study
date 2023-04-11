package behavioral.state;

public abstract class AbstractOrderState {
    /**
     * 负责状态切换的类
     */
    protected OrderContext orderContext;

    public AbstractOrderState(OrderContext orderContext) {
        this.orderContext = orderContext;
    }

    public abstract void payOrder();

    public abstract void deliver();

    public abstract void receiveGoods();
}
