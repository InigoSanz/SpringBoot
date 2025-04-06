package com.iem.gestion_empleado.apirest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoDto {
	
	private String id;
	private String nombre;
	private String descripcion;
}