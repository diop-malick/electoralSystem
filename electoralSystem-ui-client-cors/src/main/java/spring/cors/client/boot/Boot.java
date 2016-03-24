package spring.cors.client.boot;

import org.springframework.boot.SpringApplication;

import spring.cors.client.config.WebConfig;

public class Boot {

	public static void main(String[] args) {
		SpringApplication.run(WebConfig.class, args);
	}
}
