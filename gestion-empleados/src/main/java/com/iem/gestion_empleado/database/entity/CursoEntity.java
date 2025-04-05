package com.iem.gestion_empleado.database.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("CURSOS")
public class CursoEntity {
	
	@Id
	private String id;
	private String nombre;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
// 	private List<EmpleadoEntity> empleados; No guardamos la lista de empleados para evitar relaciones circulares
}