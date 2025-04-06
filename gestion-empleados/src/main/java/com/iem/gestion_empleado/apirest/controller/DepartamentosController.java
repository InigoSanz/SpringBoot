package com.iem.gestion_empleado.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iem.gestion_empleado.apirest.dto.DepartamentoCrearDto;
import com.iem.gestion_empleado.apirest.dto.DepartamentoDto;
import com.iem.gestion_empleado.service.DepartamentoService;

@RestController
@RequestMapping("/departments")
public class DepartamentosController {
	
	@Autowired
	DepartamentoService departamentoService;
	
	@PostMapping
	public ResponseEntity<DepartamentoDto> crearDepartamento(@RequestBody DepartamentoCrearDto crearDepartamentoDto) {
		
		DepartamentoDto departamentoCreado = departamentoService.crearDepartamento(crearDepartamentoDto);
		
		return ResponseEntity.ok(departamentoCreado);
	}
	
	@DeleteMapping("/{depId}")
	public ResponseEntity<Void> eliminarDepartamento(@PathVariable("depId") String id) {
		
		boolean departamentoEliminado = departamentoService.eliminarDepartamento(id);
		
		if (!departamentoEliminado) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<DepartamentoDto>> obtenerDepartamentos() {
		
		List<DepartamentoDto> listaDepartamentos = departamentoService.obtenerTodos();
		
		return ResponseEntity.ok(listaDepartamentos);
	}
}