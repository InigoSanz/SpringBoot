package com.iem.acceso_parking.domain.query;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ObtenerCochesQuery {
	
	private String matricula;
	private LocalDateTime fechaEntrada;
}