package com.iem.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class IemClassesJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IemClassesJpaApplication.class, args);
	}
}