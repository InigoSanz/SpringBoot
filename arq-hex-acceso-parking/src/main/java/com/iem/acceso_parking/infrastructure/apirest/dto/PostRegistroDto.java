package com.iem.acceso_parking.infrastructure.apirest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRegistroDto {
	
	private byte[] imagen;
	private String matricula;
}