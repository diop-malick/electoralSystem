package elections.client.cors.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	// -------------------------------- configuration couche [web]
	@Autowired
	private ApplicationContext context;

	@Bean
	public DispatcherServlet dispatcherServlet() {
		DispatcherServlet servlet = new DispatcherServlet((WebApplicationContext) context);
		return servlet;
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
		return new ServletRegistrationBean(dispatcherServlet, "/*");
	}

	@Bean
	public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
		// le service web fonctionnera sur le port 8081
		return new TomcatEmbeddedServletContainerFactory("", 8081);
	}

	// permet de définir des ressources statiques, ç-à-d des ressources non traitées par la [DispatcherServlet] 
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/*.html").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/*.js").addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/*.css").addResourceLocations("classpath:/static/css/");
	}
}
