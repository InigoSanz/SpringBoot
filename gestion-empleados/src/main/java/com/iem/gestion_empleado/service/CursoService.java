package com.iem.gestion_empleado.service;

import java.util.List;

import com.iem.gestion_empleado.apirest.dto.CursoCrearDto;
import com.iem.gestion_empleado.apirest.dto.CursoDto;

public interface CursoService {
	
	public CursoDto crearCurso(CursoCrearDto dto);
	
	boolean eliminarCurso(String id);
	
	public List<CursoDto> obtenerTodos();
}