package com.example.demo;

import java.util.List;

public class Persona {
	
	private String nombre;
	private String apellido;
	private String dni;
	private int edad;
	private List<Vehiculo> vehiculos;
	private List<Vivienda> viviendas;
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}
	
	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
	
	public List<Vivienda> getViviendas() {
		return viviendas;
	}
	
	public void setViviendas(List<Vivienda> viviendas) {
		this.viviendas = viviendas;
	}
}