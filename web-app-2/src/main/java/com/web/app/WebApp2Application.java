package com.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WebApp2Application {

	public static void main(String[] args) {
		SpringApplication.run(WebApp2Application.class, args);
	}

}
