package com.iem.jpa.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iem.jpa.entity.Access;
import com.iem.jpa.entity.Accions;
import com.iem.jpa.repository.AccessRepository;

@RestController
@RequestMapping("/access")
public class AccessController {
	
	@Autowired
	private AccessRepository accessRepository;
	
	@GetMapping
	public ResponseEntity<List<Access>> getAllAccess() {
		
		List<Access> salida = accessRepository.findAll();
		
		if (salida.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(salida);
	}
	
	@PostMapping
	public ResponseEntity<Void> postAccess(@RequestBody Access access) {
		
		access.setId(null);
		access.setFecha(LocalDateTime.now());

		Access accessSalida = accessRepository.save(access);
		
		System.out.println(accessSalida.getId());
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/vehiculo")
	public ResponseEntity<String> obtenerVehiculosAunDentro(@RequestParam("matricula") String matricula) {
		List<Access> accesosEntrada = accessRepository.findByMatriculaAndAccionOrderByFechaDesc(matricula, Accions.ENTRADA);
		List<Access> accesosSalida = accessRepository.findByMatriculaAndAccionOrderByFechaDesc(matricula, Accions.SALIDA);
		
		if (accesosSalida.isEmpty() && accesosEntrada.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else if (accesosSalida.isEmpty() && !accesosEntrada.isEmpty()) {
			return ResponseEntity.ok(matricula);
		} else if (!accesosSalida.isEmpty() && !accesosEntrada.isEmpty()) {
			if (accesosEntrada.get(0).getFecha().isAfter(accesosSalida.get(0).getFecha())) {
				return ResponseEntity.ok(matricula);
			} else {
				return ResponseEntity.noContent().build();
			}
		}
		return ResponseEntity.noContent().build();
	}
}