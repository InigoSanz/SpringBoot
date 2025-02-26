package com.web.app;

import java.util.Random;

import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServicioLlamadas {
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	@Scheduled(fixedDelay = 1000L)
	public void lanzarLlamadas() {
		Random random = new Random();
		String entradaDelOtroServicio;
		if (random.nextBoolean()) {
			entradaDelOtroServicio = "Tarea 1";
		} else {
			entradaDelOtroServicio = "Tarea X";
		}
		
		String host = "http://localhost:8080/llamada-interna?entrada=" + entradaDelOtroServicio;
		restTemplate.exchange(host, HttpMethod.GET, null, String.class);
	}
	
	
}