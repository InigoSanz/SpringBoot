package com.iem.proyectos.utils;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Utilidades {
	
	/**
	 * Método para crear una URI recibiendo un parámetro.
	 * 
	 * @param id
	 * @return URI
	 */
	public static URI crearUri(String id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
}