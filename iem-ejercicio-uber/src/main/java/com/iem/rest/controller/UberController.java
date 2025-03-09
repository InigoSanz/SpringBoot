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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iem.rest.dto.Conductor;
import com.iem.rest.dto.TipoDocumento;
import com.iem.rest.dto.Vehiculo;
import com.iem.rest.dto.Viaje;

/**
 * Controlador REST de la aplicación.
 */
@RestController
@RequestMapping("/uber")
public class UberController {

	private static final Logger log = LoggerFactory.getLogger(UberController.class);

	List<Vehiculo> vehiculos = new ArrayList<>();
	List<Conductor> conductores = new ArrayList<>();
	List<Viaje> viajes = new ArrayList<>();
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
	 * Obtiene la información de un vehículo según su número de bastidor.
	 * @param nBastidor
	 * @return vehiculo
	 */
	@GetMapping("/vehiculos/{nBastidor}")
	public ResponseEntity<Vehiculo> getVehiculo(@PathVariable("nBastidor") String nBastidor) {
		log.debug("Obtener un vehículo");
		
		for (Vehiculo vehiculo : vehiculos) {
			if (vehiculo.getnBastidor().equals(nBastidor)) {
				return ResponseEntity.ok(vehiculo);
			}
		}
		
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * Obntiene los conductores de un vehículo según su número de bastidor.
	 * @param nBastidor
	 * @return conductores de un vehículo
	 */
	@GetMapping("/vehiculos/{nBastidor}/conductores")
	public ResponseEntity<List<Conductor>> getConductoresVehiculo(@PathVariable("nBastidor") String nBastidor) {
		log.debug("Obtener conductores de vehículos");
		
		for(Vehiculo vehiculo : vehiculos) {
			if (vehiculo.getnBastidor().equals(nBastidor)) {
				if (!vehiculo.getConductores().isEmpty()) {
					return ResponseEntity.ok(vehiculo.getConductores());
				} else {
					return ResponseEntity.noContent().build();
				}
			}
		}
		
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * Obntiene los viajes de un vehículo según su número de bastidor.
	 * @param nBastidor
	 * @return viajes de un vehículo
	 */
	@GetMapping("/vehiculos/{nBastidor}/viajes")
	public ResponseEntity<List<Viaje>> getViajesVehiculo(@PathVariable("nBastidor") String nBastidor) {
		log.debug("Obtener viajes de vehículos");
		
		for(Vehiculo vehiculo : vehiculos) {
			if (vehiculo.getnBastidor().equals(nBastidor)) {
				if (!vehiculo.getViajes().isEmpty()) {
					return ResponseEntity.ok(vehiculo.getViajes());
				} else {
					return ResponseEntity.noContent().build();
				}
			}
		}
		
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * Dar de alta Conductores con: el nombre, los apellidos, el tipo de documento de identidad y el número del documento de identidad.
	 * @param conductor
	 * @return
	 */
	@PostMapping("/conductor")
	public ResponseEntity<Void> darAltaConductor(@RequestBody Conductor conductor) { // Al poner request body, Spring necesita que le enviemos algun dato por json, si no falla
		log.debug("Alta conductor");

		conductor.setNombre("Iñigo");
		conductor.setTipoDocumento(TipoDocumento.DNI);
		conductor.setDocumento("73109122B");
		
		conductores.add(conductor);
		
		URI uri = crearUri(conductor.getDocumento());
		
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * Consultar Conductores y que nos devuelva: el nombre, apellidos, el tipo de documento de identidad, el número del documento de identidad, los taxis usados y los kilómetros en cada uno de ellos.
	 * @return lista de conductores
	 */
	@GetMapping("/conductores")
	public ResponseEntity<List<Conductor>> getConductores() {
		log.debug("Obtener conductores");
		
		if (!conductores.isEmpty()) {
			return ResponseEntity.ok(conductores);
		}
		
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Obtiene la información de un conductor según su documento.
	 * @param documento
	 * @return conductor
	 */
	@GetMapping("/conductores/{documento}")
	public ResponseEntity<Conductor> getConductor(@PathVariable("documento") String documento) {
		log.debug("Obtener un conductor");
		
		for (Conductor conductor : conductores) {
			if (conductor.getDocumento().equals(documento)) {
				return ResponseEntity.ok(conductor);
			}
		}
		
		return ResponseEntity.notFound().build();
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