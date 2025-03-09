package com.iem.rest.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.iem.rest.controller.UberController;
import com.iem.rest.dto.Vehiculo;

@Service
public class ComprobarItvService {
	
	private static final Logger log = LoggerFactory.getLogger(UberController.class);
	
	Vehiculo vehiculo = new Vehiculo();
	
	public ComprobarItvService() {
		
	}
	
	@Scheduled
	public void comprobarItv() {
		log.info("Comprobando estado de la ITV...");
		
		LocalDateTime ahora = LocalDateTime.now(); // De momento vamos a utilizar la fecha actual como referencia, pero habría que utilizar la fecha de la última ITV que ha pasado
		LocalDateTime revision = vehiculo.getFechaSiguienteItv(); // Fijamos la fecha a un año vista
		
		for (Vehiculo vehiculo : vehiculos) {
			
		}
	}
}