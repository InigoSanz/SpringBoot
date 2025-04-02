package com.iem.extra.infrastructure.database.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("MEMBERS")
public class MemberEntity {
	
	@Id
	private String id;
	private String nombre;
	private String dni;
	private String proyectId;
}