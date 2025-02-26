package com.web.app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controlador {
	
	@GetMapping("/llamada-interna")
	public ResponseEntity<String> llamadaInterna(@RequestParam String entrada) {
		System.out.println("Ha llegado la llamada.");
		
		String salida;
		if (entrada != null && entrada.equalsIgnoreCase("tarea1")) {
			System.out.println("Tarea 1");
			salida = "Tarea 1 realizada.";
		} else {
			System.out.println("Sin tarea");
			salida = "No hay tareas que realizar.";
		}
		
		return ResponseEntity.ok(salida);
	}
}