package com.iem.acceso_parking.infrastructure.apirest.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iem.acceso_parking.application.port.ParkingServiceInputPort;
import com.iem.acceso_parking.domain.command.PagarTicketCommand;
import com.iem.acceso_parking.domain.query.ObtenerCochesQuery;
import com.iem.acceso_parking.domain.query.ObtenerCosteTicketQuery;
import com.iem.acceso_parking.infrastructure.apirest.dto.PostRegistroDto;
import com.iem.acceso_parking.infrastructure.apirest.mapper.ParkingMapper;

@RestController
@RequestMapping("/parkings")
@SuppressWarnings("rawtypes")
public class ParkingController {
	
	@Autowired
	ParkingServiceInputPort service;
	
	@Autowired
	ParkingMapper parkingMapper;
	
	@PostMapping
	public ResponseEntity crearRegistroEntrada(@RequestBody PostRegistroDto dto) {
		
		String idRegistro = service.crearRegistroEntrada(parkingMapper.map(dto));
		
		URI locationHeader = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(idRegistro)
				.toUri();
		
		return ResponseEntity.created(locationHeader).build();
	}
	
	@GetMapping("/coches")
	public ResponseEntity obtenerCoches(@RequestParam("matricula") String matricula, @RequestParam("fechaEntrada") LocalDateTime fechaEntrada) {
			
		ObtenerCochesQuery query = ObtenerCochesQuery.builder()
				.fechaEntrada(fechaEntrada)
				.matricula(matricula)
				.build();
		
		List<String> salida = service.obtenerCoches(query );
		
		if (salida.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("{/{id-registro}/costes")
	public ResponseEntity obtenerCosteTicket(@PathVariable("id-registro") String id) {
		
		float coste = 0.0f;
		
		try {
			coste = service.obtenerCosteTicket(ObtenerCosteTicketQuery.builder().id(null).build());
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(coste);
	}
	
	@PutMapping("/{id-registro}")
	public ResponseEntity pagarTicket(@PathVariable("id-registro") String id) {
		
		try {
			service.pagarTicket(PagarTicketCommand.builder().id(id).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity validarSalida() {
		
	}
}