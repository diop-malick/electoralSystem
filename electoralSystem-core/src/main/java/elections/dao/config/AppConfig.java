package elections.dao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import({DaoConfig.class, MetierConfig.class})
public class AppConfig {

}
