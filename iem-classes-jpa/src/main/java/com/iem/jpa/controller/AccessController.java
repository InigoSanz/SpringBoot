package com.iem.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iem.jpa.entity.Access;
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
}