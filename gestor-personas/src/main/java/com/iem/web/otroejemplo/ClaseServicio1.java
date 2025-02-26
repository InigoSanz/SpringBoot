package com.iem.web.otroejemplo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnExpression("${tipo.clase1:false} == true")
public class ClaseServicio1 implements InterfazServicio {
	
	public ClaseServicio1() {
		System.out.println("Clase 1");
	}
}