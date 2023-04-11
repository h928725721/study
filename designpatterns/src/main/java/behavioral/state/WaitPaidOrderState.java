package behavioral.state;

public class WaitPaidOrderState extends AbstractOrderState {
    public WaitPaidOrderState(OrderContext orderContext) {
        super(orderContext);
    }

    @Override
    //相当于待支付的状态绑定了支付行为
    public void payOrder() {
        System.out.println("支付成功");
        //切换状态
        this.orderContext.setState(this.orderContext.waitDeliver);
    }

    @Override
    public void deliver() {
        System.out.println("对不起，请先付钱");
    }

    @Override
    public void receiveGoods() {
        System.out.println("对不起，请先付钱");
    }
}
