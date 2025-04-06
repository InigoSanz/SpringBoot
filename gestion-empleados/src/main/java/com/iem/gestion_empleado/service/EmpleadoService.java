package com.iem.gestion_empleado.service;

import java.util.List;

import com.iem.gestion_empleado.apirest.dto.CursoDto;
import com.iem.gestion_empleado.apirest.dto.DepartamentoDto;
import com.iem.gestion_empleado.apirest.dto.EmpleadoCrearDto;
import com.iem.gestion_empleado.apirest.dto.EmpleadoDto;

public interface EmpleadoService {
	
	public EmpleadoDto crearEmpleado(EmpleadoCrearDto dto);
	
	public List<EmpleadoDto> obtenerTodosEmpleados();
	
	public EmpleadoDto obtenerEmpleadoPorId(String id);

	public EmpleadoDto actualizarEmpleado(String id, EmpleadoCrearDto empleadoDto);

	public boolean eliminarEmpleado(String id);

	public boolean asignarDepartamento(String id, String departamentoId);

	public boolean eliminarDepartamento(String id, String departamentoId);

	public List<DepartamentoDto> obtenerDepartamentosDeEmpleado(String id);

	public DepartamentoDto obtenerDepartamentoDeEmpleado(String id, String departamentoId);

	public boolean asignarCurso(String id, String cursoId);

	public boolean eliminarCurso(String id, String cursoId);

	public List<CursoDto> obtenerCursosDeEmpleado(String id);

	public CursoDto obtenerCursoDeEmpleado(String id, String cursoId);
}