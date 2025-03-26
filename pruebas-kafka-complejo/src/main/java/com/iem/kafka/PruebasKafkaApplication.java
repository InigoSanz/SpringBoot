package com.iem.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ComponentScan
@SpringBootApplication
public class PruebasKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebasKafkaApplication.class, args);
	}
}