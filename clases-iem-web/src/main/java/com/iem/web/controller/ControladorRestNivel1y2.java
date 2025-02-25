package com.iem.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iem.web.dto.Chat;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/chats")
@CrossOrigin(origins = "${SECURITY_CORS_ALLOWED_ORIGINS:}")
public class ControladorRestNivel1y2 {
	
	private static final Logger log = LoggerFactory.getLogger(ControladorRestNivel1y2.class);
	
	private Long nextId;
	private final List<Chat> chats;
	
	public ControladorRestNivel1y2() {
		chats = new ArrayList<>();
		nextId = 1l;
	}
	
	@GetMapping
	public ResponseEntity<List<Chat>> getChats(@RequestParam(value = "activos", required = false) Boolean activos) {
		log.debug("getChats");;
		
		List<Chat> chatsFiltrados = chats.stream()
				.filter(chat -> chat.getActivo().booleanValue())
				.toList();
		
		if (!chatsFiltrados.isEmpty()) {
			return ResponseEntity.ok(chatsFiltrados);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	
}