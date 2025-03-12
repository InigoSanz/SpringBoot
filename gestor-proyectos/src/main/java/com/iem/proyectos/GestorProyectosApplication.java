package com.iem.proyectos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class GestorProyectosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestorProyectosApplication.class, args);
	}
}