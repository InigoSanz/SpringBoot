package com.iem.gestion_empleado.apirest.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoDto {
	
	private String id;
	private String nombre;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
}