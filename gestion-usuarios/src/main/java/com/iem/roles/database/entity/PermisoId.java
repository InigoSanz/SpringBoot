package com.iem.roles.database.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class PermisoId implements Serializable {
	
	private static final long serialVersionUID = -1915140386753135545L;
	
	String idRol;
	String permiso;
}