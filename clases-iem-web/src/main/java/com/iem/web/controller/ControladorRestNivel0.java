package com.iem.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

@RestController
public class ControladorRestNivel0 {
	
	private static final Logger log = LoggerFactory.getLogger(ControladorRestNivel0.class);
	
	//private final Map<Integer, String> messages = new HashMap<>();
	
	@PostConstruct
	public void postConstruct() {
		log.info("Alguna info");
		log.debug("Mensaje más técnico");
		log.error("Mensaje de error");
	}
	
//	@RequestMapping("/prueba1")
//	public void prueba1() {
//		log.info("init prueba1");
//	}
//	
//	@RequestMapping
//	public void prueba() {
//		log.info("init prueba");
//	}
//	
//	@RequestMapping("/prueba2")
//	public String prueba2() {
//		log.info("init prueba 2");
//		return "Prueba 2 String";
//	}
//	
//	@RequestMapping("/prueba3")
//	public String prueba3(String entrada) {
//		log.info("init prueba 3");
//		return "Prueba 3 String " + entrada;
//	}
//	
//	@RequestMapping(value = "/addMessage", method = RequestMethod.GET)
//	public ResponseEntity<Integer> addMenssage(@RequestParam String message) {
//		log.info("Init addMessage");
//		Integer nuevoId = messages.size() + 1;
//		messages.put(nuevoId, message);
//		log.debug("Message: {} - {}", nuevoId, message);
//		return ResponseEntity.ok(nuevoId);	
//	}
//	
//	@RequestMapping(value = "/getMessage", method = RequestMethod.GET)
//	public ResponseEntity<String> getMenssage(@RequestParam Integer id) {
//		log.info("Init getMessage {}", id);
//		if (messages.containsKey(id)) {
//			return ResponseEntity.ok(messages.get(id));
//		}
//		return ResponseEntity.notFound().build();
//	}
//	
//	@RequestMapping(value = "/getAllMessages", method = RequestMethod.GET)
//	public ResponseEntity<Map<Integer, String>> getAllMenssage() {
//		log.info("Init getAllMessages");
//		return ResponseEntity.ok(messages);
//	}
//	
//	@RequestMapping(value = "/deleteMessages", method = RequestMethod.GET)
//	public ResponseEntity<Void> deleteMenssage(@RequestParam Integer id) {
//		log.info("Init deleteMessages {}", id);
//		if (messages.containsKey(id)) {
//			messages.remove(id);
//			log.debug("Message deleted");
//			return ResponseEntity.noContent().build();
//		}
//		return ResponseEntity.badRequest().build();
//	}
}