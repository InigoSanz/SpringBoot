package com.iem.rest.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.iem.rest.controller.UberController;
import com.iem.rest.dto.Vehiculo;

/**
 * Clase para los servicios.
 */
@Service
public class ComprobarItvService {
	
	private static final Logger log = LoggerFactory.getLogger(UberController.class);
	
	private List<Vehiculo> vehiculos = new ArrayList<>();
	
//	Vehiculo vehiculo = new Vehiculo();
	
	public ComprobarItvService(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
	
	/**
	 * Tarea programada para comprobar las ITVs de los vehículos.
	 */
	@Scheduled
	public void comprobarItv() {
		log.info("Comprobando estado de la ITV...");
		
		LocalDateTime ahora = LocalDateTime.now(); // De momento vamos a utilizar la fecha actual como referencia, pero habría que utilizar la fecha de la última ITV que ha pasado
//		LocalDateTime revision = vehiculo.getFechaSiguienteItv(); // Fijamos la fecha a un año vista
		
		for (Vehiculo vehiculo : vehiculos) {
			LocalDateTime revision = vehiculo.getFechaSiguienteItv(); // Cogemos la fecha de la siguiente ITV de cada vehículo
			if (revision.isBefore(ahora)) {
				vehiculo.setDisponible(false);
				log.info("El vehículo no esta disponible para el viaje"); // Lo suyo sería mostrar que vehiculo es el que no esta disponible y cual si
			}
		}
	}
}