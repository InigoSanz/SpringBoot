package com.demo.arq.application.port.input;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.arq.domain.exception.BusinessException;
import com.demo.arq.domain.model.Pecera;

import jakarta.validation.Valid;

public interface PeceraServiceInputPort {
	
	public Page<Pecera> obtenerPeceras(@Valid Pageable pageable) throws BusinessException;
		
	public Optional<Pecera> obtenerPecera(@Valid String id);
	
	public String crearPecera(@Valid Pecera input);
	
	public void modificacionParcialPecera(@Valid Pecera input) throws BusinessException;
	
	public void modificacionTotalPecera(@Valid Pecera input) throws BusinessException;
	
	public void eliminarPecera(@Valid String id) throws BusinessException;
}