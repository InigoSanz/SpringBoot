package com.demo.arq.infrastructure.database.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Document("PECERAS")
public class PeceraEntity {
	@Id
	private String id;
	private String value;
	private ValueObjectEntity valueObject;
	private boolean eliminado;
}