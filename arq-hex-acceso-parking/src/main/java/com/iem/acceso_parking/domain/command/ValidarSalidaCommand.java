package com.iem.acceso_parking.domain.command;

import lombok.Data;

@Data
public class ValidarSalidaCommand {
	
	private String matricula;
	private byte[] imagenNueva;
}