package com.iem.proyectos.apirest.dto;

public class TareaDto {
	
	// Attributes
	private String id;
	private String nombre;
	private EstadoTarea estado;
	
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

	public EstadoTarea getEstado() {
		return estado;
	}

	public void setEstado(EstadoTarea estado) {
		this.estado = estado;
	}
}