package com.iem.acceso_parking.domain.command;

import lombok.Data;

@Data
public class CrearRegistroEntradaCommand {
	
	private byte[] imagen;
	private String matricula;
}