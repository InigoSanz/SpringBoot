package com.iem.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObjectoPruebas {
	
	private String propiedad1;
	
	private float propiedad2;
	private int propiedad3;
	private boolean propiedad4;
	
}