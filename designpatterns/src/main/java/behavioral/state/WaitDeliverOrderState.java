package behavioral.state;

public class WaitDeliverOrderState extends AbstractOrderState {
    public WaitDeliverOrderState(OrderContext orderContext) {
        super(orderContext);
    }

    @Override
    public void payOrder() {
        System.out.println("你已经付过钱了");
    }

    @Override
    public void deliver() {
        System.out.println("商品已发货并送达目的地");
        //切换状态
        this.orderContext.setState(this.orderContext.receiveGoods);
    }

    @Override
    public void receiveGoods() {
        System.out.println("请稍等，商品即将发货");
    }
}
