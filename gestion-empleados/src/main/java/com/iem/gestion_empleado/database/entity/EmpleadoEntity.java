package com.iem.gestion_empleado.database.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("EMPLEADOS")
public class EmpleadoEntity {
	
	@Id
	private String id; // En vez de Long, utilizamos String por que mongo genera los IDs como String (ObjectId)
	private String nombre;
	private String correo;
	private LocalDate fechaContratacion;
	private double salario;
	private List<String> departamentosIds; // En Mongo es mejor guardar solo el ID para evitar relaciones circulares
	private List<String> cursosIds;
}