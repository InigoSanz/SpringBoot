package com.iem.web.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iem.web.dto.Chat;
import com.iem.web.dto.Mensaje;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/chats")
@CrossOrigin(origins = "${SECURITY_CORS_ALLOWED_ORIGINS:}")
public class ControladorRestNivel1y2 {
	
	private static final Logger log = LoggerFactory.getLogger(ControladorRestNivel1y2.class);
	
	private Long nextId;
	private final List<Chat> chats;
	private Long nextMensajeId;
	
	public ControladorRestNivel1y2() {
		chats = new ArrayList<>();
		nextId = 1l;
	}
	
	@GetMapping
	public ResponseEntity<List<Chat>> getChats(@RequestParam(value = "activos", required = false) Boolean activos) {
		log.debug("getChats");
		
		List<Chat> chatsFiltrados = chats.stream()
				.filter(chat -> chat.getActivo())
				.toList();
		
		if (!chatsFiltrados.isEmpty()) {
			return ResponseEntity.ok(chatsFiltrados);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Void> postChat(@Valid @RequestBody Chat chat) {
		log.debug("postChat");
		
		chat.setId(nextId++);
		chat.setActivo(Boolean.TRUE);
		chat.setFechaCreacion(LocalDateTime.now());
		chat.setMensajes(new ArrayList<>());
		
		chats.add(chat);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(chat.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteChats() {
		log.debug("deleteChats");
		
		chats.stream().forEach(chat -> {
			if (chat.getActivo().booleanValue()) {
				chat.setActivo(Boolean.FALSE);
			}
		});
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id-chat}")
	public ResponseEntity<Chat> getChat(@Valid @PathVariable("id-chat") Long chatId) {
		log.debug("getChat");
		
		Optional<Chat> chatFiltrados = chats.stream()
				.filter(chat -> chat.getActivo().booleanValue() && chat.getId().equals(chatId))
				.findFirst();
		
		if(chatFiltrados.isPresent()) {
			return ResponseEntity.ok(chatFiltrados.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@DeleteMapping("/{id-chat}")
	public ResponseEntity<Void> deleteChat(@Valid @PathVariable("id-chat") Long chatId) {
		log.debug("deleteChat");
		
		chats.stream().forEach(chat -> {
			if (chat.getActivo().booleanValue() && chat.getId().equals(chatId)) {
				chat.setActivo(Boolean.FALSE);
			}
		});
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id-chat}/mensajes")
	public ResponseEntity<List<Mensaje>> getMensajes(@Valid @PathVariable("id-chat") Long chatId) {
		log.debug("getMensajes");
		
		List<Mensaje> chatsFiltrados = chats.stream()
				.filter(chat -> chat.getActivo().booleanValue() && chat.getId().equals(chatId))
				.flatMap(chat -> chat.getMensajes().stream())
				.toList();
		
		if (!chatsFiltrados.isEmpty()) {
			return ResponseEntity.ok(chatsFiltrados);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping("/{id-chat}/mensajes")
	public ResponseEntity<Void> postMensaje (@Valid @PathVariable("id-chat") Long chatId,
			@Valid @RequestBody Mensaje mensaje) {
		log.debug("postMensaje");
		
		 boolean cambiado = false;
		
		for (Chat chat : chats) {
			if (chat.getActivo().booleanValue() && chat.getId().equals(chatId)) {
				mensaje.setId(nextMensajeId++);
				mensaje.setFecha(LocalDateTime.now());
				chat.getMensajes().add(mensaje);
				cambiado = true;
			}
		}
		
		if (cambiado) {
			URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(mensaje.getId())
					.toUri();
			
			return ResponseEntity.created(uri).build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id-chat}/mensajes")
	public ResponseEntity<Void> deleteMensajes(@Valid @PathVariable("id-chat") Long chatId) {
		log.debug("deleteMensajes");
		
		chats.forEach(chat -> {
			if (chat.getActivo().booleanValue() && chat.getId().equals(chatId)) {
				chat.getMensajes().clear();
			}
		});
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id-chat}/mensajes/{id-men}")
	public ResponseEntity<Mensaje> getMensajes(@Valid @PathVariable("id-chat") Long chatId,
			@Valid @PathVariable("id-men") Long mensajeId) {
		log.debug("getMensaje");
		
		Optional<Mensaje> mensajeFiltrado = chats.stream()
				.filter(chat -> chat.getId().equals(chatId))
				.flatMap(chat -> chat.getMensajes().stream())
				.filter(mensaje -> mensaje.getId() == mensajeId)
				.findFirst();
		
		if (mensajeFiltrado.isPresent()) {
			return ResponseEntity.ok(mensajeFiltrado.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@DeleteMapping("/{id-chat}/mensajes/{id-men}")
	public ResponseEntity<Void> deleteMensaje(@Valid @PathVariable("id-chat") Long chatId,
			@Valid @PathVariable("id-men") Long mensajeId) {
		log.debug("deleteMensaje");
		
		for (int indChat = 0; indChat < chats.size(); indChat++) {
			if (chats.get(indChat).getActivo().booleanValue() && chats.get(indChat).getId().equals(chatId)) {
				
				for (int indMen = 0; indChat < chats.get(indChat).getMensajes().size(); indMen++) {
					if (chats.get(indChat).getMensajes().get(indMen).getId() == mensajeId) {
						chats.get(indChat).getMensajes().remove(indMen);
						break;
					}
				}
				break;
			}
		}
		return ResponseEntity.noContent().build();
	}
	
}