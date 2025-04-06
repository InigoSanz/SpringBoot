package com.iem.gestion_empleado.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iem.gestion_empleado.service.CursoService;

@RestController
@RequestMapping("/courses")
public class CursoController {
	
	@Autowired
	CursoService cursoService;
	
	
}