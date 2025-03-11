package com.iem.mongo.entity;

public class Detalle {
	
	private String valor;
	
	@Override
	public String toString() {
		return "Detalle [valor=" + valor + "]";
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}