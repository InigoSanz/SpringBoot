package com.demo.arq.infrastructure.database.mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.demo.arq.domain.model.Pecera;
import com.demo.arq.domain.model.ValueObject;
import com.demo.arq.infrastructure.database.entity.PeceraEntity;
import com.demo.arq.infrastructure.database.entity.ValueObjectEntity;

@Mapper(componentModel = "spring")
public interface PeceraToPeceraEntityMapper {
	
	@Mapping(target = "valueZ", source = "domain.value")
	@Mapping(target = "eliminado", ignore = true)
	public PeceraEntity fromDomainToEntity(Pecera domain);
	
	@Mapping(target = "value", source = "entity.valueZ")
	@InheritInverseConfiguration
	public Pecera fromEntityToDomain(PeceraEntity entity);
	
	public ValueObjectEntity fromDomainToEntity(ValueObject domain);
	
	default ValueObject fromEntityToDomain(ValueObjectEntity entity) {
		return ValueObject.builder().value(entity.getValue()).build();
	}

	default Page<Pecera> fromEntityToDomain(Page<PeceraEntity> pageEntity) {
		return pageEntity == null ? null
				: new PageImpl<>(
						fromEntityToDomain(pageEntity.getContent()),
						pageEntity.getPageable(),
						pageEntity.getTotalElements());
	}

	public List<Pecera> fromEntityToDomain(List<PeceraEntity> content);

	default Optional<Pecera> fromEntityToDomain(Optional<PeceraEntity> recursoEntity) {
		return recursoEntity.isPresent() ? 
				Optional.<Pecera>of(fromEntityToDomain(recursoEntity.get())) :
					Optional.<Pecera>empty();
	}
}