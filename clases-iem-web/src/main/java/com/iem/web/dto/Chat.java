package com.iem.web.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public class Chat {
	
	@Null
	private Long id;
	
	@NotEmpty
	@Size(min = 3, max = 255)
	private String nombre;
	
	@Size(max = 255)
	private String descripcion;
	
	@Null
	private LocalDateTime fechaCreacion;
	
	@Null
	private Boolean activo;
	
	@Null
	private List<Mensaje> mensajes;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
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
	
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	
	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public Boolean getActivo() {
		return activo;
	}
	
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	public List<Mensaje> getMensajes() {
		return mensajes;
	}
	
	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}
}