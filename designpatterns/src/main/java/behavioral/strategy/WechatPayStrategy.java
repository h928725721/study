package behavioral.strategy;

import java.math.BigDecimal;

/**
 * 微信支付
 */
public class WechatPayStrategy implements PayStrategy{

    @Override
    public boolean pay(int money) {
        System.out.println("微信支付成功");
        return true;
    }

    @Override
    public BigDecimal queryBalance(String accountNo) {
        System.out.println("微信余额10元");
        return new BigDecimal(10);
    }
}
