package com.iem.gestion_empleado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.gestion_empleado.apirest.dto.EmpleadoCrearDto;
import com.iem.gestion_empleado.apirest.dto.EmpleadoDto;
import com.iem.gestion_empleado.database.entity.EmpleadoEntity;
import com.iem.gestion_empleado.database.repository.EmpleadoRepository;
import com.iem.gestion_empleado.mapper.EmpleadoMapper;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
	
	@Autowired
	EmpleadoRepository empleadoRepository;
	
	@Autowired
	EmpleadoMapper empleadoMapper;
	
	@Override
	public List<EmpleadoDto> obtenerTodosEmpleados() {
		
		List<EmpleadoEntity> empleados = empleadoRepository.findAll();
		
		return empleadoMapper.empleadoEntityListToDtoList(empleados);
	}

	@Override
	public EmpleadoDto crearEmpleado(EmpleadoCrearDto dto) {
		
		EmpleadoEntity entidad = empleadoMapper.empleadoCrearDtoToEntity(dto);
		EmpleadoEntity guardar = empleadoRepository.save(entidad);
		
		return empleadoMapper.empleadoEntityToDto(guardar);
	}
}