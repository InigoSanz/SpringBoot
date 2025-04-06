package com.iem.gestion_empleado.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<List<EmpleadoDto>> obtenerEmpleados() {
		
		return ResponseEntity.ok(empleadoService.obtenerTodosEmpleados());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmpleadoDto> obtenerEmpleado(@PathVariable("id") String id) {
		
		EmpleadoDto empleado = empleadoService.obtenerEmpleadoPorId(id);
		
		if (empleado == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(empleado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmpleadoDto> actualizarEmpleado(@PathVariable("id") String id, @RequestBody EmpleadoCrearDto empleadoDto) {
		
		EmpleadoDto empleadoActualizar = empleadoService.actualizarEmpleado(id, empleadoDto);
		
		if (empleadoActualizar == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(empleadoActualizar);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarEmpleado(@PathVariable("id") String id) {
		
		boolean empleadoEliminado = empleadoService.eliminarEmpleado(id);
		
		if (!empleadoEliminado) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}
}