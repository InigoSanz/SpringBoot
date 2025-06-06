package com.iem.gestion_empleado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class GestionEmpleadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionEmpleadosApplication.class, args);
	}
}