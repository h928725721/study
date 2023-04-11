package behavioral.strategy.jdk;

import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PayTypeService {


    @Resource
    PluginRegistry<PayStrategy, PayEnum> registry;


    public boolean pay(PayEnum payEnum,int payNumber) {
        Optional<PayStrategy> pluginFor = registry.getPluginFor(payEnum);
        PayStrategy payStrategy = pluginFor.get();
        return payStrategy.pay(payNumber);
    }

    public BigDecimal queryBalance(PayEnum payEnum) {
        Optional<PayStrategy> pluginFor = registry.getPluginFor(payEnum);
        PayStrategy payStrategy = pluginFor.get();
        return payStrategy.queryBalance("xxx");
    }

}
