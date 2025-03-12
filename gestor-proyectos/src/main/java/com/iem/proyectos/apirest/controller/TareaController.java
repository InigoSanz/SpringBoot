package com.iem.proyectos.apirest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iem.proyectos.apirest.dto.EstadoTarea;
import com.iem.proyectos.apirest.dto.TareaDto;
import com.iem.proyectos.database.entity.ProyectoEntity;
import com.iem.proyectos.database.entity.TareaEntity;
import com.iem.proyectos.database.repository.ProyectoRepository;
import com.iem.proyectos.database.repository.TareaRepository;
import com.iem.proyectos.utils.Utilidades;

@RestController
@RequestMapping("/projects/{id-project}/tasks")
public class TareaController {

	@Autowired
	TareaRepository tareaRepository;

	@Autowired
	ProyectoRepository proyectoRepository;

	@GetMapping
	public ResponseEntity<List<TareaDto>> obtenerTareas(@PathVariable("id-project") String idProyecto) {

		List<TareaEntity> listaGuardada = tareaRepository.findByIdProyecto(idProyecto);
		List<TareaDto> listaSalida = new ArrayList<>();

		for (TareaEntity entidad : listaGuardada) {
			TareaDto tareaSalida = new TareaDto();
			tareaSalida.setId(entidad.getId());
			tareaSalida.setNombre(entidad.getNombre());
			
		if (EstadoTarea.validarValor(entidad.getEstado())) {
			tareaSalida.setEstado(EstadoTarea.valueOf(entidad.getEstado()));
		} else {
			tareaSalida.setEstado(null);
		}

			listaSalida.add(tareaSalida);
		}

		if (!listaSalida.isEmpty()) {
			return ResponseEntity.ok(listaSalida);
		}

		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<Void> crearTarea(@PathVariable("id-project") String idProjecto, @RequestBody TareaDto tarea) {

		Optional<ProyectoEntity> proyectoGuardado = proyectoRepository.findById(idProjecto);

		if (proyectoGuardado.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}

		TareaEntity tareaGuardar = new TareaEntity();
		tareaGuardar.setNombre(tarea.getNombre());
		tareaGuardar.setEstado(tarea.getEstado().name()); // Para obtener el nombre (String) del ENUM
		tareaGuardar.setIdProyecto(idProjecto);

		TareaEntity tareaGuardada = tareaRepository.save(tareaGuardar);

		return ResponseEntity.created(Utilidades.crearUri(tareaGuardada.getId())).build();
	}
}