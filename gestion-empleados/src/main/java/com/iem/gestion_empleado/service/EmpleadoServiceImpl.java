package com.iem.gestion_empleado.service;

import java.util.List;
import java.util.Optional;

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

	@Override
	public EmpleadoDto obtenerEmpleadoPorId(String id) {
		
		Optional<EmpleadoEntity> empleado = empleadoRepository.findById(id);
		
		if (empleado.isPresent()) {
			return empleadoMapper.empleadoEntityToDto(empleado.get());
		} else {
			return null;
		}
	}

	@Override
	public EmpleadoDto actualizarEmpleado(String id, EmpleadoCrearDto empleadoDto) {
		
		Optional<EmpleadoEntity> empleadoOptional = empleadoRepository.findById(id);
		
		if (empleadoOptional.isPresent()) {
			EmpleadoEntity empleadoEntidad = empleadoOptional.get();
			
			empleadoMapper.actualizarEntityDeDto(empleadoDto, empleadoEntidad);
			
			EmpleadoEntity empleadoGuardar = empleadoRepository.save(empleadoEntidad);
			
			return empleadoMapper.empleadoEntityToDto(empleadoGuardar);
		} else {
			return null;
		}
	}

	@Override
	public boolean eliminarEmpleado(String id) {
		
		Optional<EmpleadoEntity> empleado = empleadoRepository.findById(id);
		
		if (empleado.isPresent()) {
			empleadoRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}