package com.iem.extra.infrastructure.database.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("PROJECTS")
public class ProjectEntity {
	
	private String id;
	private String nombre;
}