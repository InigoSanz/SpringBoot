package com.iem.acceso_parking.infrastructure.apirest.mapper;

import org.mapstruct.Mapper;

import com.iem.acceso_parking.domain.command.CrearRegistroEntradaCommand;
import com.iem.acceso_parking.infrastructure.apirest.dto.PostRegistroDto;

@Mapper(componentModel = "spring")
public interface ParkingMapper {
	
	public CrearRegistroEntradaCommand map(PostRegistroDto dto);
}