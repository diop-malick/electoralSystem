package elections.webjson.boot;

import org.springframework.boot.SpringApplication;

import elections.webjson.config.WebConfig;

public class Boot {

	public static void main(String[] args) {
		SpringApplication.run(WebConfig.class, args);
	}
}
