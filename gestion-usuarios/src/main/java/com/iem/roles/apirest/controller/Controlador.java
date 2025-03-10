package com.iem.roles.apirest.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iem.roles.apirest.dto.PostUsuarioDto;
import com.iem.roles.apirest.dto.UsuarioResponseDto;
import com.iem.roles.database.entity.Usuario;
import com.iem.roles.database.service.GestionUsuarioService;

@RestController
public class Controlador {

	@Autowired
	GestionUsuarioService gestionUsuarioService;

	@PostMapping("/usuarios")
	public ResponseEntity<Void> crearUsuario(@RequestBody PostUsuarioDto usuarioEntrada) {
		Usuario usuarioRepo = new Usuario();
		usuarioRepo.setNombre(usuarioEntrada.getNombre());
		usuarioRepo.setCorreo(usuarioEntrada.getCorreo());
		usuarioRepo.setFechaCreacion(LocalDateTime.now());

		URI uri = crearUri(usuarioRepo.getId());

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/usuarios/{id-usuario}")
	public ResponseEntity<Void> actualizarUsuario(@PathVariable("id-usuario") Long idUsuario,
			@RequestBody PostUsuarioDto usuarioEntrada) {

		Optional<Usuario> usuarioOpt = gestionUsuarioService.obtenerUsuario(idUsuario);

		if (usuarioOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Usuario usuarioRepo = new Usuario();
		usuarioRepo.setId(idUsuario);
		usuarioRepo.setNombre(usuarioEntrada.getNombre());
		usuarioRepo.setCorreo(usuarioEntrada.getCorreo());

		gestionUsuarioService.actualizarUsuario(usuarioRepo);

		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/usuarios/{id-usuario}")
	public ResponseEntity<Void> actualizarUsuarioParcial(@PathVariable("id-usuario") Long idUsuario,
			@RequestBody PostUsuarioDto usuarioEntrada) {

		Optional<Usuario> usuarioOpt = gestionUsuarioService.obtenerUsuario(idUsuario);

		if (usuarioOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Usuario usuarioRepo = new Usuario();
		usuarioRepo.setId(idUsuario);

		if (usuarioEntrada.getNombre() != null) {
			usuarioRepo.setNombre(usuarioEntrada.getNombre());
		}

		if (usuarioEntrada.getCorreo() != null) {
			usuarioRepo.setCorreo(usuarioEntrada.getCorreo());
		}

		gestionUsuarioService.actualizarUsuario(usuarioRepo);

		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/usuarios/{id-usuario}")
	public ResponseEntity<UsuarioResponseDto> obtenerUsuario(@PathVariable("id-usuario") Long idUsuario) {
		
		Optional<Usuario> usuarioOpt = gestionUsuarioService.obtenerUsuario(idUsuario);
		
		if (usuarioOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		UsuarioResponseDto usuarioSalida = new UsuarioResponseDto();
		usuarioSalida.setId(idUsuario);
		usuarioSalida.setNombre(usuarioOpt.get().getNombre()); // Para recuperar un atributo de un Optional hay que utilizar el .get()
		usuarioSalida.setCorreo(usuarioOpt.get().getCorreo());
		
		return ResponseEntity.ok(usuarioSalida );
	}

	/**
	 * Método para generar URIs.
	 * 
	 * @param id
	 * @return URI
	 */
	private URI crearUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
}