package com.example.demo;

public class Vehiculo {
	
	private String matricula;
	private Modelos modelo;
	private int kilometros;
	private boolean servicioSecreto;
	
	
	
	// Constructor
	public Vehiculo(String matricula, Modelos modelo, int kilometros, boolean servicioSecreto) {
		super();
		this.matricula = matricula;
		this.modelo = modelo;
		this.kilometros = kilometros;
		this.servicioSecreto = servicioSecreto;
	}
	
	// Getters y Setters
	public String getMatricula() {
		return matricula;
	}
	
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public Modelos getModelo() {
		return modelo;
	}
	
	public void setModelo(Modelos modelo) {
		this.modelo = modelo;
	}
	
	public int getKilometros() {
		return kilometros;
	}
	
	public void setKilometros(int kilometros) {
		this.kilometros = kilometros;
	}
	
	public boolean isServicioSecreto() {
		return servicioSecreto;
	}
	
	public void setServicioSecreto(boolean servicioSecreto) {
		this.servicioSecreto = servicioSecreto;
	}

	@Override
	public String toString() {
		return "Vehiculo [matricula=" + matricula + ", modelo=" + modelo + ", kilometros=" + kilometros
				+ ", servicioSecreto=" + servicioSecreto + "]";
	}
}