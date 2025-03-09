package com.iem.rest.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iem.rest.dto.Vehiculo;

/**
 * Controlador REST de la aplicación.
 */
@RestController("/uber")
public class UberController {

	private static final Logger log = LoggerFactory.getLogger(UberController.class);

	List<Vehiculo> vehiculos = new ArrayList<>();
	LocalDateTime ahora = LocalDateTime.now();

	@PostMapping("/vehiculo")
	public ResponseEntity<Void> darAltaVehiculo(@RequestBody Vehiculo vehiculo) {
		log.debug("Alta vehículo");

		String numeroBastidor = UUID.randomUUID().toString();
		
		vehiculo.setnBastidor(numeroBastidor);
		vehiculo.setMatricula("9994MCB");
		vehiculo.setFechaSiguienteItv(ahora.plusMonths(6));
		
		vehiculos.add(vehiculo);
		
		URI uri = crearUri(numeroBastidor);
		
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * Método para generar URIs.
	 * 
	 * @param id
	 * @return URI
	 */
	private URI crearUri(String id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
}