package com.iem.inversion_dependencia.entrada;

import org.springframework.beans.factory.annotation.Autowired;

import com.iem.inversion_dependencia.negocio.Interfaz2;

public class Clase1 implements Interfaz1 {
	
	@Autowired
	Interfaz2 clase2;
}