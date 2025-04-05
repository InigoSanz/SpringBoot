package com.iem.gestion_empleado.apirest.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoCrearDto {
	
	private String nombre;
	private String correo;
	private LocalDate fechaContratacion;
	private double salario;
	private List<String> departamentosIds;
	private List<String> cursosIds;
}