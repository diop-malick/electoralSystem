package elections.client.cors.boot;

import org.springframework.boot.SpringApplication;

import elections.client.cors.config.WebConfig;

public class Boot {

	public static void main(String[] args) {
		SpringApplication.run(WebConfig.class, args);
	}
}
