package behavioral.strategy.comment;


import org.springframework.stereotype.Service;

/**
 * 微信支付
 */
@PayType(payType = PayEnum.WECHAT_PAY)
@Service
public class WechatPay implements PayStrategy {

    @Override
    public boolean pay(int money) {
        System.out.println("微信支付成功");
        return true;
    }

}
