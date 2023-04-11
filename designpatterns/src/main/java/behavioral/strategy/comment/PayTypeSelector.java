package behavioral.strategy.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PayTypeSelector {


    private Map<PayEnum, PayStrategy> strategyMap;

    @Autowired
    private void setPayStrategies(List<PayStrategy> payStrategies) {
        strategyMap = payStrategies.stream()
                .collect(Collectors.toMap(p -> Objects.requireNonNull(AnnotationUtils.findAnnotation(p.getClass(), PayType.class)).payType(), p -> p));
    }

    public void pay(PayEnum payType,int amount){
        PayStrategy payStrategy = strategyMap.get(payType);
        payStrategy.pay(amount);
    }
}
