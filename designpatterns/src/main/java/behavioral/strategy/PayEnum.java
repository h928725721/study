package behavioral.strategy;


public enum PayEnum {
    /**
     * 支付宝支付
     */
    AliPay("aliPay",new AliPayStrategy()),
    /**
     * 微信支付
     */
    WechatPay("wechatPay",new WechatPayStrategy());
    
    private final String key;
    private final PayStrategy value;

    PayEnum(String key, PayStrategy value) {
        this.key = key;
        this.value = value;
    }
    public static PayStrategy getValue(String key){
        for (PayEnum payEnum : PayEnum.values()){
            if (payEnum.key.equals(key)){
                return payEnum.value;
            }
        }
        //没有合适key则默认阿里支付
       return new AliPayStrategy();
    }
}
