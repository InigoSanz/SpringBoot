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

import com.iem.gestion_empleado.apirest.dto.CursoCrearDto;
import com.iem.gestion_empleado.apirest.dto.CursoDto;
import com.iem.gestion_empleado.service.CursoService;

@RestController
@RequestMapping("/courses")
public class CursoController {
	
	@Autowired
	CursoService cursoService;
	
	@PostMapping
	public ResponseEntity<CursoDto> crearCurso(@RequestBody CursoCrearDto cursoDto) {
		
		CursoDto cursoCrear = cursoService.crearCurso(cursoDto);
		
		return ResponseEntity.ok(cursoCrear);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarCurso(@PathVariable("id") String id) {
		
		boolean cursoEliminado = cursoService.eliminarCurso(id);
		
		if(!cursoEliminado) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<CursoDto>> obtenerCursos() {
		
		List<CursoDto> listaCursos = cursoService.obtenerTodos();
		
		return ResponseEntity.ok(listaCursos);
	}
}