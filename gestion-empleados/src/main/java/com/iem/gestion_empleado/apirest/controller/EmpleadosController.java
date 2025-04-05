package com.iem.gestion_empleado.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iem.gestion_empleado.apirest.dto.EmpleadoCrearDto;
import com.iem.gestion_empleado.apirest.dto.EmpleadoDto;
import com.iem.gestion_empleado.service.EmpleadoService;

@RestController
@RequestMapping("/employees")
public class EmpleadosController {
	
	@Autowired
	EmpleadoService empleadoService;
	
	@PostMapping
	public ResponseEntity<EmpleadoDto> crearEmpleado(@RequestBody EmpleadoCrearDto empleadoDto) {
		
		EmpleadoDto nuevoEmpleado = empleadoService.crearEmpleado(empleadoDto);
		
		return ResponseEntity.ok(nuevoEmpleado);
	}
	
	@GetMapping
	public ResponseEntity<List<EmpleadoDto>> getEmpleados() {
		
		return ResponseEntity.ok(empleadoService.obtenerTodosEmpleados());
	}
}