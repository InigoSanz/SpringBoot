package com.iem.mock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class IemPruebasMockApplication {

	public static void main(String[] args) {
		SpringApplication.run(IemPruebasMockApplication.class, args);
	}
}