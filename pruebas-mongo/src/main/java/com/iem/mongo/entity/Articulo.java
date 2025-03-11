package com.iem.mongo.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("ARTICULOS")
public class Articulo {

	@Id
	private String id;
	private String nombre;
	private Dimensiones dimensiones;
	private List<Detalle> detalles;
	
	@Override
	public String toString() {
		return "Articulo [id=" + id + ", nombre=" + nombre + ", dimensiones=" + dimensiones + ", detalles=" + detalles
				+ "]";
	}
	
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

	public Dimensiones getDimensiones() {
		return dimensiones;
	}

	public void setDimensiones(Dimensiones dimensiones) {
		this.dimensiones = dimensiones;
	}

	public List<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}
}