package behavioral.strategy;

public class Test {
    public static void main(String[] args) {
        String payType = "alipay";
        PayStrategy payStrategy = PayEnum.getValue(payType);
        payStrategy.pay(10);
        payStrategy.queryBalance("xxx");

    }


}
