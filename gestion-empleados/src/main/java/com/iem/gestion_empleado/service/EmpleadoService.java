package com.iem.gestion_empleado.service;

import java.util.List;

import com.iem.gestion_empleado.apirest.dto.EmpleadoCrearDto;
import com.iem.gestion_empleado.apirest.dto.EmpleadoDto;

public interface EmpleadoService {
	
	public EmpleadoDto crearEmpleado(EmpleadoCrearDto dto);
	
	public List<EmpleadoDto> obtenerTodosEmpleados();
}