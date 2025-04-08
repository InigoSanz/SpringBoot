package com.iem.acceso_parking.infrastructure.database.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;

import com.iem.acceso_parking.domain.model.RegistroEntrada;
import com.iem.acceso_parking.infrastructure.database.entity.RegistroEntradaEntity;

@Mapper(componentModel = "spring")
public interface RegistroEntradaEntityMapper {

	public RegistroEntradaEntity map(RegistroEntrada domain);
	
	public RegistroEntrada map(RegistroEntradaEntity registroEntradaEntity);

	default Optional<RegistroEntrada> map(Optional<RegistroEntradaEntity> salida) {
		return salida.isEmpty() ? Optional.empty() : Optional.ofNullable(map(salida.get()));
	}
}