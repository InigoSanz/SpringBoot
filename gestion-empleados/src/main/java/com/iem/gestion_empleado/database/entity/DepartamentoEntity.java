package com.iem.gestion_empleado.database.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("DEPARTAMENTOS")
public class DepartamentoEntity {
	
	@Id
	private String id;
	private String nombre;
	private String descripcion;
//	private List<EmpleadoEntity> empleados; No guardamos la lista de empleados para evitar relaciones circulares
}