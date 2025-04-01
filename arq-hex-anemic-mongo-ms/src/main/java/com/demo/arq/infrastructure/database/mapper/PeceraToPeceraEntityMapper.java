package com.demo.arq.infrastructure.database.mapper;

import org.mapstruct.Mapper;

import com.demo.arq.domain.model.Pecera;
import com.demo.arq.domain.model.ValueObject;
import com.demo.arq.infrastructure.database.entity.PeceraEntity;
import com.demo.arq.infrastructure.database.entity.ValueObjectEntity;

@Mapper(componentModel = "spring")
public interface PeceraToPeceraEntityMapper {
	
	public PeceraEntity fromDomainToEntity(Pecera domain);
	
	public Pecera fromEntityToDomain(PeceraEntity entity);
	
	public ValueObjectEntity fromDomainToEntity(ValueObject domain);
	
	public ValueObject fromEntityToDomain(ValueObjectEntity entity);
}