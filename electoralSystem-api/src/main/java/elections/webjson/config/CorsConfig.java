package elections.webjson.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * CORS : Spring MVC traite lui-même les commandes HTTP [OPTIONS] avec un traitement par défaut. Aussi c'est toujours Spring qui répond
 * 
 * @author Malick
 *
 */
@Configuration
public class CorsConfig {

	// Enabling CORS for the whole application
	// By default all origins and GET, HEAD and POST methods are allowed.
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
				
				//can easily change any properties
				/*
				.allowedOrigins("http://domain2.com")
				.allowedMethods("PUT", "DELETE")
				.allowedHeaders("header1", "header2", "header3")
				.exposedHeaders("header1", "header2")
				.allowCredentials(false).maxAge(3600);
				*/
			}
		};
	}
	
	


}
