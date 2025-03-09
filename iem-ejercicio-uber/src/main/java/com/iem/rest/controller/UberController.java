package com.iem.rest.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iem.rest.dto.Vehiculo;

/**
 * Controlador REST de la aplicación.
 */
@RestController
@RequestMapping("/uber")
public class UberController {

	private static final Logger log = LoggerFactory.getLogger(UberController.class);

	List<Vehiculo> vehiculos = new ArrayList<>();
	LocalDateTime ahora = LocalDateTime.now();
	
	/**
	 * Dar de alta Vehículos con: el número de bastidor, la matrícula y fecha de siguiente ITV.
	 * @param vehiculo
	 * @return
	 */
	@PostMapping("/vehiculo")
	public ResponseEntity<Void> darAltaVehiculo(@RequestBody Vehiculo vehiculo) { // Al poner request body, Spring necesita que le enviemos algun dato por json, si no falla
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
	 * Consultar Vehículos y que nos devuelva: el número de bastidor, la matrícula, los kilómetros recorridos totales y fecha de siguiente ITV.
	 * @return vehiculos
	 */
	@GetMapping("/vehiculos")
	public ResponseEntity<List<Vehiculo>> getVehiculos() {
		log.debug("Obtener vehículos");
		
		if (!vehiculos.isEmpty()) {
			return ResponseEntity.ok(vehiculos);
		}
		
		return ResponseEntity.noContent().build();
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