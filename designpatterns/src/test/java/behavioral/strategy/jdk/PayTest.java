package behavioral.strategy.jdk;

import behavioral.App;
import behavioral.strategy.comment.PayTypeSelector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = App.class)
public class PayTest {

    @Autowired
    PayTypeService payTypeService;

    @Test
    public void name() {
        payTypeService.pay(PayEnum.ALI_PAY, 10);
        System.out.println();

    }

    @Autowired
    PayTypeSelector selector;

    @Test
    void commentTest() {
        selector.pay(behavioral.strategy.comment.PayEnum.ALI_PAY, 10);
    }
}
