package com.iem.proyectos.apirest.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iem.proyectos.apirest.dto.ProyectoDto;
import com.iem.proyectos.database.entity.ProyectoEntity;
import com.iem.proyectos.database.repository.ProyectoRepository;
import com.iem.proyectos.utils.Utilidades;

@RestController
@RequestMapping("/projects")
public class ProyectoController {

	@Autowired
	ProyectoRepository proyectoRepository;

	@GetMapping
	public ResponseEntity<List<ProyectoDto>> obtenerProyectos() {

		List<ProyectoEntity> listaBbdd = proyectoRepository.findAll();
		List<ProyectoDto> listaSalida = new ArrayList<>();

		for (ProyectoEntity entidad : listaBbdd) {
			ProyectoDto proyectoSalida = new ProyectoDto();
			proyectoSalida.setId(entidad.getId());
			proyectoSalida.setNombre(entidad.getNombre());
			proyectoSalida.setDescripcion(entidad.getDescripcion());
			proyectoSalida.setFechaInicio(entidad.getFechaInicio());
			proyectoSalida.setFechaFinal(entidad.getFechaFinal());
			listaSalida.add(proyectoSalida);
		}

//		List<ProyectoDto> listaSalida2 = listaBbdd.stream()
//				.map(entidad -> new ProyectoDto(entidad.getId(),entidad.getNombre(), entidad.getDescripcion(), entidad.getFechaInicio(), entidad.getFechaFinal()))
//				.toList();
//		
//		System.out.println(listaSalida2);

		if (!listaBbdd.isEmpty()) {
			return ResponseEntity.ok(listaSalida);
		}

		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<Void> crearProyecto(@RequestBody ProyectoDto proyecto) {

		ProyectoEntity proyectoBbdd = new ProyectoEntity();
		proyectoBbdd.setNombre(proyecto.getNombre());
		proyectoBbdd.setDescripcion(proyecto.getDescripcion());
		proyectoBbdd.setFechaInicio(proyecto.getFechaInicio());
		proyectoBbdd.setFechaFinal(proyecto.getFechaFinal());

		/*
		 * Nos aseguramos que el ID es nulo para la inserción. Como está de entrada
		 * podrían enviarnoslo.
		 */
		proyectoBbdd.setId(null);
		proyectoBbdd.setFechaCreacion(LocalDateTime.now());
		proyectoBbdd.setMiembros(new ArrayList<>());
		proyectoBbdd.setTareas(new ArrayList<>());

		ProyectoEntity proyectoGuardado = proyectoRepository.save(proyectoBbdd);

		return ResponseEntity.created(Utilidades.crearUri(proyectoGuardado.getId())).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> actualizarProyecto(@PathVariable("id") String id, @RequestBody ProyectoDto proyecto) {

		Optional<ProyectoEntity> proyectoGuardado = proyectoRepository.findById(id);

		if (proyectoGuardado.isPresent()) {
			proyectoGuardado.get().setNombre(proyecto.getNombre());
			proyectoGuardado.get().setDescripcion(proyecto.getDescripcion());
			proyectoGuardado.get().setFechaInicio(proyecto.getFechaInicio());
			proyectoGuardado.get().setFechaFinal(proyecto.getFechaFinal());
			proyectoRepository.save(proyectoGuardado.get()); // Al estar con un Optional si no utilizamos el .get() no
																// nos va a dejar guardarlo
		}

		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Void> actualizarProyectoParcial(@PathVariable("id") String id,
			@RequestBody ProyectoDto proyecto) {

		Optional<ProyectoEntity> proyectoGuardado = proyectoRepository.findById(id);

		if (proyectoGuardado.isPresent()) {
			if (proyecto.getNombre() != null) { // En los Patch hay que comprobar que el campo que queremos actualizar
												// no sea nulo
				proyectoGuardado.get().setNombre(proyecto.getNombre());
			}
			if (proyecto.getDescripcion() != null) {
				proyectoGuardado.get().setDescripcion(proyecto.getDescripcion());
			}
			if (proyecto.getFechaInicio() != null) {
				proyectoGuardado.get().setFechaInicio(proyecto.getFechaInicio());
			}
			if (proyecto.getFechaFinal() != null) {
				proyectoGuardado.get().setFechaFinal(proyecto.getFechaFinal());
			}

			proyectoRepository.save(proyectoGuardado.get()); // Al estar con un Optional si no utilizamos el .get() no
																// nos va a dejar guardarlo
		}

		return ResponseEntity.noContent().build();
	}
}