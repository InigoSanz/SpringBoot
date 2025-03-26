package com.iem.sucursal.dto;

public class SucursalDto {
	private String id;
	TipoEvento tipoeEvento;
	private String datos;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TipoEvento getTipoeEvento() {
		return tipoeEvento;
	}

	public void setTipoeEvento(TipoEvento tipoeEvento) {
		this.tipoeEvento = tipoeEvento;
	}

	public String getDatos() {
		return datos;
	}

	public void setDatos(String datos) {
		this.datos = datos;
	}
}