package com.iem.rest.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Conductor.
 */
public class Conductor {

	// Atributos
	private String nombre;
	private String apellidos;
	private TipoDocumento tipoDocumento;
	private String documento;
	private List<Vehiculo> vehiculos = new ArrayList<>();
	private List<Viaje> viajes = new ArrayList<>();
	
	// Constructores
	public Conductor() {
		// Constructor por defecto
	}
	
	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public List<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(List<Viaje> viajes) {
		this.viajes = viajes;
	}
}