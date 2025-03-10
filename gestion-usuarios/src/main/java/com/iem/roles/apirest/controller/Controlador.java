package com.iem.roles.apirest.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iem.roles.apirest.dto.PostRolDto;
import com.iem.roles.apirest.dto.PostUsuarioDto;
import com.iem.roles.apirest.dto.RolResponseDto;
import com.iem.roles.apirest.dto.UsuarioResponseDto;
import com.iem.roles.database.entity.Rol;
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

		Long idUsuario = gestionUsuarioService.crearUsuario(usuarioRepo);

		URI uri = crearUri(idUsuario);

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/usuarios/{id-usuario}")
	public ResponseEntity<Void> actualizarUsuario(@PathVariable("id-usuario") Long idUsuario,
			@RequestBody PostUsuarioDto usuarioEntrada) {

		Usuario usuarioRepo = new Usuario();
		usuarioRepo.setId(idUsuario);
		usuarioRepo.setNombre(usuarioEntrada.getNombre());
		usuarioRepo.setCorreo(usuarioEntrada.getCorreo());

		boolean haActualizado = gestionUsuarioService.actualizarUsuario(usuarioRepo);

		if (haActualizado == true) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/usuarios/{id-usuario}")
	public ResponseEntity<Void> actualizarUsuarioParcial(@PathVariable("id-usuario") Long idUsuario,
			@RequestBody PostUsuarioDto usuarioEntrada) {

		Usuario usuarioRepo = new Usuario();
		usuarioRepo.setId(idUsuario);

		if (usuarioEntrada.getNombre() != null) {
			usuarioRepo.setNombre(usuarioEntrada.getNombre());
		}

		if (usuarioEntrada.getCorreo() != null) {
			usuarioRepo.setCorreo(usuarioEntrada.getCorreo());
		}

		boolean haActualizado = gestionUsuarioService.actualizarUsuario(usuarioRepo);

		if (haActualizado == true) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/usuarios")
	public ResponseEntity<List<UsuarioResponseDto>> obtenerUsuarios() {

		List<Usuario> listaGuardada = gestionUsuarioService.obtenerUsuarios();
		List<UsuarioResponseDto> listaSalida = listaGuardada.stream().map(usuario -> {
			UsuarioResponseDto usuarioNuevo = new UsuarioResponseDto();
			usuarioNuevo.setId(usuario.getId());
			usuarioNuevo.setNombre(usuario.getNombre());
			usuarioNuevo.setCorreo(usuario.getCorreo());
			return usuarioNuevo;
		}).toList();

		return ResponseEntity.ok(listaSalida);
	}

	@GetMapping("/usuarios/{id-usuario}")
	public ResponseEntity<UsuarioResponseDto> obtenerUsuario(@PathVariable("id-usuario") Long idUsuario) {

		Optional<Usuario> usuarioOpt = gestionUsuarioService.obtenerUsuario(idUsuario);

		if (usuarioOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		UsuarioResponseDto usuarioSalida = new UsuarioResponseDto();
		usuarioSalida.setId(idUsuario);
		usuarioSalida.setNombre(usuarioOpt.get().getNombre()); // Para recuperar un atributo de un Optional hay que
																// utilizar el .get()
		usuarioSalida.setCorreo(usuarioOpt.get().getCorreo());

		return ResponseEntity.ok(usuarioSalida);
	}

	@DeleteMapping("/usuarios/{id-usuario}")
	public ResponseEntity<Void> eliminarUsuario(@PathVariable("id-usuario") Long idUsuario) {

		gestionUsuarioService.eliminarUsuario(idUsuario);

		return ResponseEntity.noContent().build();
	}

	@PostMapping("/usuarios/{id-usuario}/roles")
	public ResponseEntity<Void> crearRol(@PathVariable("id-usuario") Long idUsuario,
			@RequestBody PostRolDto postRolDto) {

		Rol nuevoRol = new Rol();
		nuevoRol.setNombre(postRolDto.getNombre());
		nuevoRol.setDescripcion(postRolDto.getDesc());
		nuevoRol.setIdUsuario(idUsuario);

		Rol rolGuardado = gestionUsuarioService.crearRol(nuevoRol);

		if (rolGuardado == null) {
			return ResponseEntity.badRequest().build();
		}

		URI uri = crearUri2(rolGuardado);
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/roles")
	public ResponseEntity<List<RolResponseDto>> obtenerRoles() {
		List<Rol> listaGuardada = gestionUsuarioService.obtenerRoles();
		List<RolResponseDto> salida = listaGuardada.stream().map(rol -> {
			RolResponseDto nuevoObjeto = new RolResponseDto();
			nuevoObjeto.setNombre(rol.getNombre());
			nuevoObjeto.setId(rol.getId());
			nuevoObjeto.setDesc(rol.getDescripcion());
			return nuevoObjeto;
		}).toList();
		return ResponseEntity.ok(salida);
	}

	@GetMapping("/usuarios/{id-usuario}/roles")
	public ResponseEntity<List<RolResponseDto>> obtenerRolesPorUsuario(@PathVariable("id-usuario") Long idUsuario) {
		List<Rol> listaGuardada = gestionUsuarioService.obtenerRolesPorUsuario(idUsuario);
		List<RolResponseDto> salida = listaGuardada.stream().map(rol -> {
			RolResponseDto nuevoObjeto = new RolResponseDto();
			nuevoObjeto.setNombre(rol.getNombre());
			nuevoObjeto.setId(rol.getId());
			nuevoObjeto.setDesc(rol.getDescripcion());
			return nuevoObjeto;
		}).toList();
		return ResponseEntity.ok(salida);
	}

	/**
	 * Método para generar URIs con Long.
	 * 
	 * @param id
	 * @return URI
	 */
	private URI crearUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
	
	/**
	 * Método para generar URIs con Rol.
	 * 
	 * @param id
	 * @return URI
	 */
	private URI crearUri2(Rol id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
}