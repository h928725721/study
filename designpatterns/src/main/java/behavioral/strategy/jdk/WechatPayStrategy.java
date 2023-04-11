package behavioral.strategy.jdk;


import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class WechatPayStrategy implements PayStrategy {

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

    @Override
    public boolean supports(@NonNull PayEnum payEnum) {
        return PayEnum.WECHAT_PAY.equals(payEnum);

    }
}
