package behavioral.strategy;

import java.math.BigDecimal;

/**
 * 支付宝支付
 */
public class AliPayStrategy implements PayStrategy{

    @Override
    public boolean pay(int money) {
        System.out.println("支付宝支付成功");
        return true;
    }

    @Override
    public BigDecimal queryBalance(String accountNo) {
        System.out.println("支付宝余额10元");
        return new BigDecimal(10);
    }
}
