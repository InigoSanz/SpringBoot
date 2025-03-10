package com.iem.roles.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PERMISOS")
public class Permiso {
	
	@Id
	private PermisoId id;
}