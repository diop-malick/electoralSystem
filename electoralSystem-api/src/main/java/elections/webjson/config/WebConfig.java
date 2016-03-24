package elections.webjson.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import elections.dao.config.AppConfig;


@EnableWebMvc
@Import({ AppConfig.class, SecurityConfig.class })
@ComponentScan({ "elections.webjson.service" })
public class WebConfig {
	// -------------------------------- configuration couche [web]
	
	// injection du contexte spring 
	@Autowired
	private ApplicationContext context;

	// définition de la servlet
	@Bean
	public DispatcherServlet dispatcherServlet() {
		DispatcherServlet servlet = new DispatcherServlet((WebApplicationContext) context);
		return servlet;
	}

	// le servlet traite tous les URL
	@Bean
	public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
		return new ServletRegistrationBean(dispatcherServlet, "/*");
	}

	@Bean
	public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
		return new TomcatEmbeddedServletContainerFactory("", 8080);
	}

	// mappeur jSON
	@Bean
	public ObjectMapper jsonMapper() {
		return new ObjectMapper();
	}

}
