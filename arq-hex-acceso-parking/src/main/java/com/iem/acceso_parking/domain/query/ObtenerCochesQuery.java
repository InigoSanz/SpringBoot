package com.iem.acceso_parking.domain.query;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ObtenerCochesQuery {
	
	private String matricula;
	private LocalDateTime fechaEntrada;
}