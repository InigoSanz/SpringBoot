package com.iem.gestion_empleado.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.iem.gestion_empleado.apirest.dto.CursoCrearDto;
import com.iem.gestion_empleado.apirest.dto.CursoDto;
import com.iem.gestion_empleado.database.entity.CursoEntity;

@Mapper(componentModel = "spring")
public interface CursoMapper {
	
	@Mapping(target = "id", ignore = true)
	public CursoEntity cursoCrearToDto(CursoCrearDto dto);

	public CursoDto cursoEntityToDto(CursoEntity cursoGuardar);

	public List<CursoDto> cursoEntityListToDto(List<CursoEntity> listaCursos);

	public List<CursoDto> cursoEntityListToDtoList(List<CursoEntity> cursos);
}