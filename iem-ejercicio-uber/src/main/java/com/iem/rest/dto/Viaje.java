package com.iem.rest.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Viaje.
 */
public class Viaje {
	
	// Atributos
	private String idViaje;
	private String idVehiculo;
	private String idConductor;
	private List<Vehiculo> vehiculos  = new ArrayList<>();
	private List<Conductor> conductores = new ArrayList<>();
	private LocalDateTime tiempoViaje;
	private int kmRecorridos;
	
	// Constructores
	public Viaje() {
		// Constructor por defecto
	}
	
	// Getters y Setters
	public String getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(String idViaje) {
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

	public String getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(String idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public String getIdConductor() {
		return idConductor;
	}

	public void setIdConductor(String idConductor) {
		this.idConductor = idConductor;
	}

	public LocalDateTime getTiempoViaje() {
		return tiempoViaje;
	}

	public void setTiempoViaje(LocalDateTime tiempoViaje) {
		this.tiempoViaje = tiempoViaje;
	}

	public int getKmRecorridos() {
		return kmRecorridos;
	}

	public void setKmRecorridos(int kmRecorridos) {
		this.kmRecorridos = kmRecorridos;
	}
}