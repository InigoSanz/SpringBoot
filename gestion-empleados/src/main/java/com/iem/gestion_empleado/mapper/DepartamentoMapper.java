package com.iem.gestion_empleado.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.iem.gestion_empleado.apirest.dto.DepartamentoCrearDto;
import com.iem.gestion_empleado.apirest.dto.DepartamentoDto;
import com.iem.gestion_empleado.database.entity.DepartamentoEntity;

@Mapper(componentModel = "spring")
public interface DepartamentoMapper {
	
	@Mapping(target = "id", ignore = true)
	public DepartamentoEntity departamentoCrearDtoToEntity(DepartamentoCrearDto dto);
	
	public DepartamentoDto departamentoEntityToDto(DepartamentoEntity entity);

	public List<DepartamentoDto> departamentoEntityListToDtoList(List<DepartamentoEntity> departamentos);
}