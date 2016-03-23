package elections.ui.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import elections.dao.config.AppConfig;


@Import(AppConfig.class)
@ComponentScan(basePackages = { "elections.ui.service" })
public class UiConfig {
}
