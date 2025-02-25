package com.iem.web.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iem.web.dto.Medico;
import com.iem.web.dto.Paciente;

@RestController
public class ControladorGestorPersonas {
	
	private static final Logger log = LoggerFactory.getLogger(ControladorGestorPersonas.class);
	
	List<Medico> medicos = new ArrayList<>();
	List<Paciente> pacientes = new ArrayList<>();
	
	@PostMapping("/medicos")
	public ResponseEntity<Void> altaMedico(@RequestBody Medico medico) {
		log.debug("altaMedico");
		
		String id = UUID.randomUUID().toString();
		
		medico.setIdentificador(id);
		
		medicos.add(medico);
		
		URI uri = crearUri(id);
		
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/pacientes")
	public ResponseEntity<Void> altaPaciente(@RequestBody Paciente paciente) {
		log.debug("altaPaciente");
		
		String id = UUID.randomUUID().toString();
		
		paciente.setIdentificador(id);
		paciente.setCitasPendiente(new ArrayList<>());
		paciente.setFichaMedica(null);
		
		pacientes.add(paciente);
		
		URI uri = crearUri(id);
		
		return ResponseEntity.created(uri).build();
	}
	
	// Patch (parcial) y Put (total), uno deja nulls y el otro no ¿? ... Revisar esto !!¡¡
	@PatchMapping("/pacientes/{id-paciente}")
	public ResponseEntity<Void> modificarPaciente(@RequestBody Paciente paciente) {
		log.debug("modificarPaciente");
		
		for (Paciente pac : pacientes) {
			if (pac.getIdentificador().equalsIgnoreCase(paciente.getIdentificador())) {
				// Miramos citas pendientes
				if (paciente.getCitasPendiente() != null) {
					// Compara con las que tenemos y añadir o quitar
					pac.setCitasPendiente(paciente.getCitasPendiente());
				} else {
					// Quitar todas
					pac.setCitasPendiente(new ArrayList<>());
				}
				
				// Miramos la FichaMedica
				if (paciente.getCitasPendiente() != null) {
					pac.setFichaMedica(paciente.getFichaMedica());
				} else {
					pac.setFichaMedica(null);
				}
			}
		}
		
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Método para generar URIs.
	 * @param id 
	 * @return URI
	 */
	private URI crearUri(String id) {
		return ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(id)
				.toUri();
	}

}