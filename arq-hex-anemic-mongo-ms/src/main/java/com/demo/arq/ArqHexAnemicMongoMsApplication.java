package com.demo.arq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.demo.arq"})
public class ArqHexAnemicMongoMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArqHexAnemicMongoMsApplication.class, args);
	}

}
