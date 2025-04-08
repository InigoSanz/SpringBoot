package com.iem.acceso_parking.infrastructure.database.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("REGISTROS_ENTRADA")
public class RegistroEntradaEntity {
	
	@Id
	private String id;
	private String matricula;
	private String idImagenContentManager;
	private boolean pagado;
	private LocalDateTime fechaPagado;
	private LocalDateTime fechaEntrada;
	private LocalDateTime fechaSalida;
}
