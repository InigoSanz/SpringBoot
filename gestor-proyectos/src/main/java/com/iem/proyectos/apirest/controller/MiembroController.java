package com.iem.proyectos.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iem.proyectos.apirest.dto.MiembroDto;
import com.iem.proyectos.database.entity.MiembroEntity;
import com.iem.proyectos.database.repository.MiembroRepository;
import com.iem.proyectos.database.repository.ProyectoRepository;
import com.iem.proyectos.database.repository.TareaRepository;
import com.iem.proyectos.utils.Utilidades;

@RestController
@RequestMapping("/projects/{id-project}/tasks/{id-task}/members")
public class MiembroController {

	@Autowired
	MiembroRepository miembroRepository;

	@Autowired
	ProyectoRepository proyectoRepository;

	@Autowired
	TareaRepository tareaRepository;

	@GetMapping
	public ResponseEntity<List<MiembroDto>> obtenerMiembro(@PathVariable("id-project") String idProyecto,
			@PathVariable("id-project") String idTarea) {

		if (!proyectoRepository.existsById(idProyecto) || !tareaRepository.existsById(idTarea)) {
			return ResponseEntity.badRequest().build();
		}

		List<MiembroEntity> listaGuardada = miembroRepository.findByIdProyectoAndIdTarea(idProyecto, idTarea);

		List<MiembroDto> listaSalida = listaGuardada.stream().map(miembro -> {
			MiembroDto salida = new MiembroDto();
			salida.setId(miembro.getId());
			salida.setNombre(miembro.getNombre());
			return salida;
		}).toList();

		return ResponseEntity.ok(listaSalida);
	}

	@PostMapping
	public ResponseEntity<Void> crearMiembro(@PathVariable("id-project") String idProyecto,
			@PathVariable("id-project") String idTarea, @RequestBody MiembroDto miembro) {
		
		if (!proyectoRepository.existsById(idProyecto) || !tareaRepository.existsById(idTarea)) {
			return ResponseEntity.badRequest().build();
		}
		
		MiembroEntity miembroGuardar = new MiembroEntity();
		miembroGuardar.setNombre(miembro.getNombre());
		miembroGuardar.setIdProyecto(idProyecto);
		miembroGuardar.setIdTarea(idTarea);
		
		MiembroEntity miembroGuardado = miembroRepository.save(miembroGuardar);
		
		return ResponseEntity.created(Utilidades.crearUri(miembroGuardado.getId())).build();
	}
}