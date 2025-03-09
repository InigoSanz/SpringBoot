package com.iem.rest.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Vehículo.
 */
public class Vehiculo {

	// Atributos
	private String nBastidor; // Cambiamos a String para utilizar UUID
	private String matricula;
	private LocalDateTime fechaSiguienteItv;
	private int kilometros;
	private List<Conductor> conductores = new ArrayList<>(); // Inicializamos la lista para el error 500 que nos da el endpoint
	private List<Viaje> viajes = new ArrayList<>();
	
	// Constructores
	public Vehiculo() {
		// Constructor por defecto
	}
	
	public Vehiculo(String nBastidor, String matricula, LocalDateTime fechaSiguienteItv) {
		this.nBastidor = nBastidor;
		this.matricula = matricula;
		this.fechaSiguienteItv = fechaSiguienteItv;
	}
	
	// Getters y Setters
	public String getnBastidor() {
		return nBastidor;
	}

	public void setnBastidor(String nBastidor) {
		this.nBastidor = nBastidor;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public LocalDateTime getFechaSiguienteItv() {
		return fechaSiguienteItv;
	}

	public void setFechaSiguienteItv(LocalDateTime fechaSiguienteItv) {
		this.fechaSiguienteItv = fechaSiguienteItv;
	}

	public int getKilometros() {
		return kilometros;
	}

	public void setKilometros(int kilometros) {
		this.kilometros = kilometros;
	}

	public List<Conductor> getConductores() {
		return conductores;
	}

	public void setConductores(List<Conductor> conductores) {
		this.conductores = conductores;
	}

	public List<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(List<Viaje> viajes) {
		this.viajes = viajes;
	}
}