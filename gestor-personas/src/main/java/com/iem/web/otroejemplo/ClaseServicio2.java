package com.iem.web.otroejemplo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnExpression("${tipo.clase1:false} == false")
public class ClaseServicio2 implements InterfazServicio {
	
	public ClaseServicio2() {
		System.out.println("Clase 2");
	}
}