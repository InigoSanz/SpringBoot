package com.iem.inversion_dependencia.negocio;

import org.springframework.beans.factory.annotation.Autowired;

public class Clase2 implements Interfaz2 {
	
	@Autowired
	Interfaz3 clase3;
}