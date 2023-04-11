package behavioral.strategy.jdk;

import org.springframework.context.annotation.Configuration;
import org.springframework.plugin.core.config.EnablePluginRegistries;

@Configuration
@EnablePluginRegistries({PayStrategy.class})
public class PayTypeConfig {
}
