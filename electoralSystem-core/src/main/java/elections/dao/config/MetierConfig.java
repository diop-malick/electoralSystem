package elections.dao.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import elections.dao.config.DaoConfig;

@Import({ DaoConfig.class })
@ComponentScan({ "elections.metier.service" })
public class MetierConfig {
}
