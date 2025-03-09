package com.iem.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // Habilitamos las scheduling para que se ejecuten las tareas programadas
public class IemEjercicioUberApplication {

	public static void main(String[] args) {
		SpringApplication.run(IemEjercicioUberApplication.class, args);
	}
}