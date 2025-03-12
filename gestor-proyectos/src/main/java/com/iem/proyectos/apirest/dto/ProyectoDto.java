package com.iem.proyectos.apirest.dto;

import java.time.LocalDate;

public class ProyectoDto {
	
	// Attributes
	private String id;
	private String nombre;
	private String descripcion;
	private LocalDate fechaInicio;
	private LocalDate fechaFinal;
	
	// Constructors
	public ProyectoDto() {
		// Por defecto
	}
	
	public ProyectoDto(String id, String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFinal) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
	}

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
}