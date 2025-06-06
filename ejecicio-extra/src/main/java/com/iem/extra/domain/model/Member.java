package com.iem.extra.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	private String id;
	private String nombre;
	private String dni;
	private String proyectId;
}