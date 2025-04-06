package com.iem.gestion_empleado.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.iem.gestion_empleado.apirest.dto.EmpleadoCrearDto;
import com.iem.gestion_empleado.apirest.dto.EmpleadoDto;
import com.iem.gestion_empleado.database.entity.EmpleadoEntity;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {
	
	public EmpleadoDto empleadoEntityToDto(EmpleadoEntity entity);
	public EmpleadoEntity empleadoDtoToEntity(EmpleadoDto dto);
	
	@Mapping(target = "id", ignore = true)
	public EmpleadoEntity empleadoCrearDtoToEntity(EmpleadoCrearDto dto);
	public EmpleadoCrearDto empleadoCrearEntityToDto(EmpleadoEntity entity);
	
	public List<EmpleadoDto>  empleadoEntityListToDtoList(List<EmpleadoEntity> entities);
	
	@Mapping(target = "id", ignore = true)
	public void actualizarEntityDeDto(EmpleadoCrearDto dto, @MappingTarget EmpleadoEntity entity);
}