package elections.client.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import(MetierConfig.class)
@ComponentScan(basePackages = { "elections.client.ui" })
public class UiConfig {
}
