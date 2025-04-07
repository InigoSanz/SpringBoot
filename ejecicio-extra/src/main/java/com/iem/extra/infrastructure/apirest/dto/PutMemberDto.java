package com.iem.extra.infrastructure.apirest.dto;

import lombok.Data;

@Data
public class PutMemberDto {
	
	private String nombre;
	private String dni;
	private String proyectId;
}
