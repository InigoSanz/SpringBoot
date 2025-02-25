package com.iem.web.dto;

import java.time.LocalDateTime;

public class FichaMedica {

	private String identificadorMedico;
	private LocalDateTime fechaUltimaRevision;

	public String getIdentificadorMedico() {
		return identificadorMedico;
	}

	public void setIdentificadorMedico(String identificadorMedico) {
		this.identificadorMedico = identificadorMedico;
	}

	public LocalDateTime getFechaUltimaRevision() {
		return fechaUltimaRevision;
	}

	public void setFechaUltimaRevision(LocalDateTime fechaUltimaRevision) {
		this.fechaUltimaRevision = fechaUltimaRevision;
	}
}