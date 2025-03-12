package com.iem.proyectos.apirest.dto;

public enum EstadoTarea {
	EN_ESPERA, EN_PROGRESO, TERMINADA;
	
	public static boolean validarValor(String valor) {
		EstadoTarea[] estados = EstadoTarea.values();
		
		for (EstadoTarea estado : estados) {
			if (estado.name().equals(valor)) {
				return true;
			}
		}
		
		return false;
	}
}