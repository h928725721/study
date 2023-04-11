package behavioral.strategy;

import java.math.BigDecimal;

public interface PayStrategy {

    /**
     * @param money 需要付款的金额
     */
    boolean pay(int money);

    /**
     * @param accountNo 需要查询的账户
     */
    BigDecimal queryBalance(String accountNo);

}
