package com.iem.rest.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	 * Consultar como subrecurso de los Conductores, los Vehículos y los Viajes.
	 * @param documento
	 * @return vehiculos de un conductor
	 */
	@GetMapping("/conductores/{documento}/vehiculos")
	public ResponseEntity<List<Vehiculo>> getVehiculosConductor(@PathVariable("documento") String documento) {
		log.debug("Obtener vehiculos de un conductor");
		
		for(Conductor conductor : conductores) {
			if (conductor.getDocumento().equals(documento)) {
				if (!conductor.getVehiculos().isEmpty()) {
					return ResponseEntity.ok(conductor.getVehiculos());
				} else {
					return ResponseEntity.noContent().build();
				}
			}
		}
		
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * Consultar como subrecurso de los Conductores, los Vehículos y los Viajes.
	 * @param documento
	 * @return viajes de un conductor
	 */
	@GetMapping("/conductores/{documento}/viajes")
	public ResponseEntity<List<Viaje>> getViajesConductor(@PathVariable("documento") String documento) {
		log.debug("Obtener viajes de un conductor");
		
		for(Conductor conductor : conductores) {
			if (conductor.getDocumento().equals(documento)) {
				if (!conductor.getViajes().isEmpty()) {
					return ResponseEntity.ok(conductor.getViajes());
				} else {
					return ResponseEntity.noContent().build();
				}
			}
		}
		
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * Dar de alta Viajes con: el id del Vehículo, el id del conductor, los kilómetros realizados y el tiempo tardado.
	 * @param viaje
	 * @return
	 */
	@PostMapping("/viaje")
	public ResponseEntity<Void> darAltaViaje(@RequestBody Viaje viaje) { // Al poner request body, Spring necesita que le enviemos algún dato por json, si no falla
		log.debug("Alta viaje");
		
		String identificadorViaje = UUID.randomUUID().toString();
		
		viaje.setIdViaje(identificadorViaje);
		viaje.setTiempoViaje(ahora.plusMinutes(80));
		viaje.setKmRecorridos(1000);
		
		viajes.add(viaje);
		
		URI uri = crearUri(identificadorViaje);
		
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * Consultar Viajes y que nos devuelva: los datos completos del Vehículo, los datos completos del Conductor, los kilómetros realizados y el tiempo tardado.
	 * @return información de los viajes
	 */
	@GetMapping("/viajes")
	public ResponseEntity<List<Viaje>> getViajes() {
		log.debug("Obtener viajes");
		
		if (!viajes.isEmpty()) {
			return ResponseEntity.ok(viajes);
		}
		
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Obtener la información de un viaje según su identificador.
	 * @param idViaje
	 * @return viaje
	 */
	@GetMapping("/viajes/{idViaje}")
	public ResponseEntity<Viaje> getViaje(@PathVariable("idViaje") String idViaje) {
		log.debug("Obtener un viaje");
		
		for (Viaje viaje : viajes) {
			if (viaje.getIdViaje().equals(idViaje)) {
				return ResponseEntity.ok(viaje);
			}
		}
		
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * Consultar como subrecurso de los Viajes, los Vehículos y los Conductores.
	 * @param idViaje
	 * @return lista de vehículos de un viaje
	 */
	@GetMapping("/viajes/{idViaje}/vehiculos")
	public ResponseEntity<List<Vehiculo>> getVehiculosViaje(@PathVariable("idViaje") String idViaje) {
		log.debug("Obtener vehiculos de un viaje");
		
		for(Viaje viaje : viajes) {
			if (viaje.getIdViaje().equals(idViaje)) {
				if (!viaje.getIdVehiculo().isEmpty()) {
					return ResponseEntity.ok(viaje.getVehiculos());
				} else {
					return ResponseEntity.noContent().build();
				}
			}
		}
		
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * Consultar como subrecurso de los Viajes, los Vehículos y los Conductores.
	 * @param idViaje
	 * @return lista de conductores de un viaje
	 */
	@GetMapping("/viajes/{idViaje}/conductores")
	public ResponseEntity<List<Conductor>> getConductoresViaje(@PathVariable("idViaje") String idViaje) {
		log.debug("Obtener conductores de un viaje");
		
		for(Viaje viaje : viajes) {
			if (viaje.getIdViaje().equals(idViaje)) {
				if (!viaje.getConductores().isEmpty()) {
					return ResponseEntity.ok(viaje.getConductores());
				} else {
					return ResponseEntity.noContent().build();
				}
			}
		}
		
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * Eliminar cualquier Vehículo o Conductor pero con “Borrado Lógico” y no “Borrado Físico”, para mantener los datos aun en BBDD.
	 * @param nBastidor
	 * @return
	 */
	@DeleteMapping("/vehiculos/{nBastidor}")
	public ResponseEntity<Void> deleteVehiculo(@PathVariable String nBastidor) {
		log.debug("Eliminado lógico de un vehículo");
		
		for (Vehiculo vehiculo : vehiculos) {
			if (vehiculo.getnBastidor().equals(nBastidor)) {
				vehiculo.setEstaActivo(false);
				return ResponseEntity.ok().build();
			}
		}
		
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * Eliminar cualquier Vehículo o Conductor pero con “Borrado Lógico” y no “Borrado Físico”, para mantener los datos aun en BBDD.
	 * @param documento
	 * @return
	 */
	@DeleteMapping("/conductores/{documento}")
	public ResponseEntity<Void> deleteConductor(@PathVariable String documento) {
		log.debug("Eliminado lógico de un conductor");
		
		for (Conductor conductor : conductores) {
			if (conductor.getDocumento().equals(documento)) {
				conductor.setEstaActivo(false);
				return ResponseEntity.ok().build();
			}
		}
		
		return ResponseEntity.notFound().build();
	}
	
	/*
	 * Los Viajes no se podrán eliminar de ninguna forma.
	 * 
	 * Para esto simplemente no añadimos el endpoint para eliminar viajes, pero podríamos meter seguridad adicional.
	 */
	
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