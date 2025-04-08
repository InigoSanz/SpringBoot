package com.iem.acceso_parking.infrastructure.database.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iem.acceso_parking.application.port.ParkingRepositoryOutputPort;
import com.iem.acceso_parking.domain.model.RegistroEntrada;
import com.iem.acceso_parking.domain.query.ObtenerCochesQuery;
import com.iem.acceso_parking.infrastructure.database.entity.RegistroEntradaEntity;
import com.iem.acceso_parking.infrastructure.database.mapper.RegistroEntradaEntityMapper;
import com.iem.acceso_parking.infrastructure.database.repository.RegistroEntradaRepository;

@Component
public class ParkingRepositoryService implements ParkingRepositoryOutputPort {
	
	@Autowired
	RegistroEntradaRepository registroEntradaRepository;
	
	@Autowired
	RegistroEntradaEntityMapper entityMapper;
	
	@Override
	public String crearRegistro(RegistroEntrada registro) {
		
		RegistroEntradaEntity paraGuardar = entityMapper.map(registro);
		
		RegistroEntradaEntity registroGuardado = registroEntradaRepository.save(paraGuardar);
		
		return registroGuardado.getId();
	}

	@Override
	public List<String> obtenerCoches(ObtenerCochesQuery query) {
		
		List<RegistroEntradaEntity> salida = registroEntradaRepository.findByFechaSalida(null);
		
		return salida.stream().map(reg -> reg.getMatricula()).toList();
	}

	@Override
	public Optional<RegistroEntrada> obtenerRegistroEntrada(String id) {
		
		Optional<RegistroEntradaEntity> salida = registroEntradaRepository.findById(id);
		
		if (salida.isEmpty()) {
			return Optional.empty();
		}
		
		return entityMapper.map(salida);
	}

	@Override
	public Optional<RegistroEntrada> obtenerRegistroEntradaPorMatricula(String matricula) {
		
		List<RegistroEntradaEntity> salida = registroEntradaRepository.findByMatriculaAndFechaSalida(matricula, null);
		
		if (salida.isEmpty()) {
			return Optional.empty();
		}
		
		return Optional.ofNullable(entityMapper.map(salida.get(0)));
	}

	@Override
	public void actualizarRegistro(RegistroEntrada registroEntrada) {
		
		registroEntradaRepository.save(entityMapper.map(registroEntrada));	
	}
}