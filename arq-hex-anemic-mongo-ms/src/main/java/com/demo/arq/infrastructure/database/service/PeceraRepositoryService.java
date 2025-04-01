package com.demo.arq.infrastructure.database.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.demo.arq.application.port.output.PeceraRepositoryOutputPort;
import com.demo.arq.domain.model.Pecera;
import com.demo.arq.infrastructure.database.entity.PeceraEntity;
import com.demo.arq.infrastructure.database.repository.PeceraRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PeceraRepositoryService implements PeceraRepositoryOutputPort {
	
	@Autowired
	PeceraRepository peceraRepository;

	@Override
	public Page<Pecera> obtenerPeceras(@Valid Pageable pageable) {
		
		return null;
	}

	@Override
	public Optional<Pecera> obtenerPecera(@Valid String id) {

		return Optional.empty();
	}

	@Override
	public String crearPecera(@Valid Pecera input) {
		log.debug("crearPecera");
		
		PeceraEntity entity = new PeceraEntity();
		
		entity.setId(null);
		entity.setEliminado(false);
		
		//entity.setValue(input.getValue());
		//entity.setValueObject(ValueObjectEntity.builder().value(input.getValueObject().getValue()).build());
		
		return peceraRepository.save(entity).getId();
	}

	@Override
	public void modificarPecera(@Valid Pecera input) {
		// Auto
	}

	@Override
	public void eliminarPecera(@Valid String id) {
		// Auto
	}
}