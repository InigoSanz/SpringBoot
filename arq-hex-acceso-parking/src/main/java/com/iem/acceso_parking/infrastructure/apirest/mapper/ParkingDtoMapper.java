package com.iem.acceso_parking.infrastructure.apirest.mapper;

import org.mapstruct.Mapper;

import com.iem.acceso_parking.domain.command.CrearRegistroEntradaCommand;
import com.iem.acceso_parking.domain.command.ValidarSalidaCommand;
import com.iem.acceso_parking.infrastructure.apirest.dto.PostRegistroDto;
import com.iem.acceso_parking.infrastructure.apirest.dto.ValidarSalidaDto;

@Mapper(componentModel = "spring")
public interface ParkingDtoMapper {
	
	public CrearRegistroEntradaCommand map(PostRegistroDto dto);
	
	public ValidarSalidaCommand map(ValidarSalidaDto dto);
}