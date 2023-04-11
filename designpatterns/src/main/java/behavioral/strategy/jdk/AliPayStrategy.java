package behavioral.strategy.jdk;


import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class AliPayStrategy implements PayStrategy {

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

    @Override
    public boolean supports(@NonNull PayEnum payEnum) {
        return PayEnum.ALI_PAY.equals(payEnum);
    }
}
