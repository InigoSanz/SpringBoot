package com.iem.gestion_empleado.service;

import java.util.List;

import com.iem.gestion_empleado.apirest.dto.DepartamentoCrearDto;
import com.iem.gestion_empleado.apirest.dto.DepartamentoDto;

public interface DepartamentoService {
	
	public DepartamentoDto crearDepartamento(DepartamentoCrearDto dto);

	public boolean eliminarDepartamento(String id);

	public List<DepartamentoDto> obtenerTodos();
}