package behavioral.state;

public class Test {
    public static void main(String[] args) {
        OrderContext orderContext = new OrderContext();
        orderContext.payOrder();
        orderContext.deliver();
        orderContext.receiveGoods();
    }
}
