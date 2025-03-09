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
	 * Un servicio programado (Scheduled), que se ejecute cada 24 horas, 
	 * que revise cuando un Vehículo debe pasar la ITV en los próximos 7 días y lo marque para que no se pueda usar durante los Viajes.
	 */
	@Scheduled(cron = "0 0 3 * * ?")	// Mirado en internet
	public void comprobarItv() {
		log.info("Comprobando estado de la ITV...");
		
		LocalDateTime ahora = LocalDateTime.now(); // De momento vamos a utilizar la fecha actual como referencia, pero habría que utilizar la fecha de la última ITV que ha pasado
		LocalDateTime comprobar = ahora.plusDays(7); // Fijamos la fecha a 7 días vista
		
		for (Vehiculo vehiculo : vehiculos) {
			LocalDateTime revision = vehiculo.getFechaSiguienteItv(); // Cogemos la fecha de la siguiente ITV de cada vehículo
			if (revision.isBefore(comprobar)) {
				vehiculo.setDisponible(false);
				log.info("El vehículo {} no esta disponible para el viaje", vehiculo.getnBastidor()); // Lo suyo sería mostrar que vehiculo es el que no esta disponible y cual si
			}
		}
	}
}