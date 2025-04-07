package com.iem.extra.infrastructure.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iem.extra.application.port.ProjectServiceInputPort;
import com.iem.extra.domain.model.Project;
import com.iem.extra.infrastructure.apirest.dto.PostProjectDto;
import com.iem.extra.infrastructure.apirest.dto.PutProjectDto;

@RestController
@RequestMapping("/projects")
@SuppressWarnings("rawtypes")
public class ProjectController {
	
	@Autowired
	ProjectServiceInputPort service;
	
	@PostMapping
	public ResponseEntity postProject(@RequestBody PostProjectDto dto) {
		
		Project project = service.crearProyecto(dto.getNombre());
		
		project.getId();
		
		// return ResponseEntity.created(null).build();
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id-project}")
	public ResponseEntity putProject(
			@PathVariable("id-project") String id,
			@RequestBody PutProjectDto dto) {
		
		try {
			service.addMember(id, dto.getId());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.noContent().build();
	}
	
	
}