package com.iem.acceso_parking.infrastructure.event.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroEntradaEventDto {
	
	private String id;
	private String matricula;
	private String idImagenContentManager;
	private boolean pagado;
	private LocalDateTime fechaPagado;
	private LocalDateTime fechaEntrada;
	private LocalDateTime fechaSalida;
}
