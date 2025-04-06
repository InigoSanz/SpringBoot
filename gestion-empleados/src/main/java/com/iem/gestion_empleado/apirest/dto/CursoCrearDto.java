package com.iem.gestion_empleado.apirest.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoCrearDto {
	
	private String nombre;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
}