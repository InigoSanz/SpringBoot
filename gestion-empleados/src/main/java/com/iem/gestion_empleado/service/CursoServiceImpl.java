package com.iem.gestion_empleado.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.gestion_empleado.apirest.dto.CursoCrearDto;
import com.iem.gestion_empleado.apirest.dto.CursoDto;
import com.iem.gestion_empleado.database.entity.CursoEntity;
import com.iem.gestion_empleado.database.repository.CursoRepository;
import com.iem.gestion_empleado.mapper.CursoMapper;

@Service
public class CursoServiceImpl implements CursoService {
	
	@Autowired
	CursoRepository cursoRepository;
	
	@Autowired
	CursoMapper cursoMapper;
	
	@Override
	public CursoDto crearCurso(CursoCrearDto dto) {
		
		CursoEntity cursoEntidad = cursoMapper.cursoCrearToDto(dto);
		CursoEntity cursoGuardar = cursoRepository.save(cursoEntidad);
		
		return cursoMapper.cursoEntityToDto(cursoGuardar);
	}

	@Override
	public boolean eliminarCurso(String id) {
		
		Optional<CursoEntity> cursoOptional = cursoRepository.findById(id);
		
		if (cursoOptional.isPresent()) {
			cursoRepository.deleteById(id);
			
			return true;
		}
		
		return false;
	}

	@Override
	public List<CursoDto> obtenerTodos() {
		
		List<CursoEntity> listaCursos = cursoRepository.findAll();
		
		return cursoMapper.cursoEntityListToDto(listaCursos);
	}
}