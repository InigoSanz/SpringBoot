package com.iem.carritos.database.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("CARRITOS")
public class Carrito {

	@Id
	private String id;

	private EstadosCarrito estado;
	private Float precioTotal;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EstadosCarrito getEstado() {
		return estado;
	}

	public void setEstado(EstadosCarrito estado) {
		this.estado = estado;
	}

	public Float getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Float precioTotal) {
		this.precioTotal = precioTotal;
	}
}