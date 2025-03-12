package com.iem.proyectos.database.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("PROYECTOS")
public class ProyectoEntity {

	// Attributes
	@Id
	private String id;
	
	private String nombre;
	private String descripcion;
	private LocalDate fechaInicio;
	private LocalDate fechaFinal;
	private LocalDateTime fechaCreacion;
	private List<String> tareas;
	private List<String> miembros;
	
	// Getters y Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(LocalDate fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public List<String> getTareas() {
		return tareas;
	}

	public void setTareas(List<String> tareas) {
		this.tareas = tareas;
	}

	public List<String> getMiembros() {
		return miembros;
	}

	public void setMiembros(List<String> miembros) {
		this.miembros = miembros;
	}
}