package com.iem.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class PruebasMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebasMongoApplication.class, args);
	}
}