package com.iem.acceso_parking.application.port;

import java.util.List;
import java.util.Optional;

import com.iem.acceso_parking.domain.model.RegistroEntrada;
import com.iem.acceso_parking.domain.query.ObtenerCochesQuery;

public interface ParkingRepositoryOutputPort {

	String crearRegistro(RegistroEntrada registro);

	List<String> obtenerCoches(ObtenerCochesQuery query);

	Optional<RegistroEntrada> obtenerRegistroEntrada(String id);
}