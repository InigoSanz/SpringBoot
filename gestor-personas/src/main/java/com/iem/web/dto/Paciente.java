package com.iem.web.dto;

import java.util.List;

public class Paciente extends Persona {
	
	private String identificador;
	private FichaMedica fichaMedica;
	private List<Cita> citasPendiente;

	public FichaMedica getFichaMedica() {
		return fichaMedica;
	}

	public void setFichaMedica(FichaMedica fichaMedica) {
		this.fichaMedica = fichaMedica;
	}

	public List<Cita> getCitasPendiente() {
		return citasPendiente;
	}

	public void setCitasPendiente(List<Cita> citasPendiente) {
		this.citasPendiente = citasPendiente;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
}