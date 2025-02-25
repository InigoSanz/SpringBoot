package com.iem.web.dto;

import java.time.LocalDateTime;

public class Cita {

	private String identificadorMedico;
	private LocalDateTime fecha;

	public String getIdentificadorMedico() {
		return identificadorMedico;
	}

	public void setIdentificadorMedico(String identificadorMedico) {
		this.identificadorMedico = identificadorMedico;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
}