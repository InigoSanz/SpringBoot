package com.iem.rest.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Viaje.
 */
public class Viaje {
	
	// Atributos
	private Long idViaje;
	private List<Vehiculo> vehiculos  = new ArrayList<>();
	private List<Conductor> conductores = new ArrayList<>();
	
	// Constructores
	public Viaje() {
		// Constructor por defecto
	}
	
	// Getters y Setters
	public Long getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(Long idViaje) {
		this.idViaje = idViaje;
	}

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public List<Conductor> getConductores() {
		return conductores;
	}

	public void setConductores(List<Conductor> conductores) {
		this.conductores = conductores;
	}
}