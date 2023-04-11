package behavioral.strategy.jdk;

import org.springframework.plugin.core.Plugin;

import java.math.BigDecimal;

public interface PayStrategy extends Plugin<PayEnum> {

    boolean pay(int money);

    BigDecimal queryBalance(String accountNo);

}
