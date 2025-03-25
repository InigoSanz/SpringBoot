package com.iem.carritos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class CarritosCompraApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarritosCompraApplication.class, args);
	}

}
