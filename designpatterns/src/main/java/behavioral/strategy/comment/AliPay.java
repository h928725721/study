package behavioral.strategy.comment;


import org.springframework.stereotype.Service;

/**
 * 支付宝支付
 */
@PayType(payType = PayEnum.ALI_PAY)
@Service
public class AliPay implements PayStrategy {

    @Override
    public boolean pay(int money) {
        System.out.println("支付宝支付成功");
        return true;
    }

}
