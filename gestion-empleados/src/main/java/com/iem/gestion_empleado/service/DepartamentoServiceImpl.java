package com.iem.gestion_empleado.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.gestion_empleado.apirest.dto.DepartamentoCrearDto;
import com.iem.gestion_empleado.apirest.dto.DepartamentoDto;
import com.iem.gestion_empleado.database.entity.DepartamentoEntity;
import com.iem.gestion_empleado.database.repository.DepartamentoRepository;
import com.iem.gestion_empleado.mapper.DepartamentoMapper;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	DepartamentoRepository departamentoRepository;

	@Autowired
	DepartamentoMapper departamentoMapper;

	@Override
	public DepartamentoDto crearDepartamento(DepartamentoCrearDto dto) {

		DepartamentoEntity departamentoEntidad = departamentoMapper.departamentoCrearDtoToEntity(dto);
		DepartamentoEntity departamentoGuardar = departamentoRepository.save(departamentoEntidad);

		return departamentoMapper.departamentoEntityToDto(departamentoGuardar);
	}

	@Override
	public boolean eliminarDepartamento(String id) {
		
		Optional<DepartamentoEntity> departamento = departamentoRepository.findById(id);

		if (departamento.isPresent()) {
			departamentoRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<DepartamentoDto> obtenerTodos() {
		
		List<DepartamentoEntity> departamentos = departamentoRepository.findAll();
		
		return departamentoMapper.departamentoEntityListToDtoList(departamentos);
	}

}