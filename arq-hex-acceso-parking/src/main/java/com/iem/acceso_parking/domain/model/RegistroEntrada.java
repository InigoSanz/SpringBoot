package com.iem.acceso_parking.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroEntrada {
	
	private String id;
	private String matricula;
	private String idImagenContentManager;
	private boolean pagado;
	private LocalDateTime fechaPagado;
	private LocalDateTime fechaEntrada;
	private LocalDateTime fechaSalida;
}
