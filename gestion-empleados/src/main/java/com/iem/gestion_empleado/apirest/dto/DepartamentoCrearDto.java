package com.iem.gestion_empleado.apirest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoCrearDto {
	
	private String nombre;
	private String descripcion;
}