package com.iem.rest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iem.rest.dto.Vehiculo;
import com.iem.rest.service.ComprobarItvService;

/**
 * Test para comprobar los servicios.
 */
@SpringBootTest
class ComprobarItvServiceTest {
	
	@Autowired
	private ComprobarItvService comprobarItvService;
	
	private List<Vehiculo> vehiculos = new ArrayList<>(); // Aquí lo puse como autowired y me peto todo, por lo visto las listas no son beans
	
	@Test
	void testComprobarItvs() {
		Vehiculo vehiculo1 = new Vehiculo();
		
		LocalDateTime ahora = LocalDateTime.now();
		
		vehiculo1.setnBastidor("BastidorPrueba");
		vehiculo1.setFechaSiguienteItv(ahora.plusDays(6));
		
		vehiculos.add(vehiculo1);
		
		comprobarItvService.comprobarItv();
		
		Assertions.assertTrue(vehiculos.get(0).isDisponible()); // Algo esta mal, ponga el día que ponga siempre pasa el test
																// Investigar el motivo
	}
}