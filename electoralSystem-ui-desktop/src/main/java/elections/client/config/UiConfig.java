package elections.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import elections.client.entities.User;

@Import(MetierConfig.class)
@ComponentScan(basePackages = { "elections.client.ui" })
public class UiConfig {
	
	  // administrateur
	  private final User ADMIN = new User("admin", "admin");

	  @Bean
	  private User admin() {
	    return ADMIN;
	  }
	  
}
