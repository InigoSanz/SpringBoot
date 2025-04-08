package com.iem.acceso_parking.infrastructure.apirest.dto;

import lombok.Data;

@Data
public class ValidarSalidaDto {
	
	private String matricula;
	private byte[] imagenNueva;
}