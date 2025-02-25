package com.example.demo;

public class Vivienda {
	
	private int habitaciones;
	private String direccion;
	private boolean propietario;
	
	public int getHabitaciones() {
		return habitaciones;
	}
	
	public void setHabitaciones(int habitaciones) {
		this.habitaciones = habitaciones;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public boolean isPropietario() {
		return propietario;
	}

	public void setPropietario(boolean propietario) {
		this.propietario = propietario;
	}
}