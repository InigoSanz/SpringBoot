package com.iem.acceso_parking.application.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.acceso_parking.application.port.ContentManagerOutputPort;
import com.iem.acceso_parking.application.port.ParkingRepositoryOutputPort;
import com.iem.acceso_parking.application.port.ParkingServiceInputPort;
import com.iem.acceso_parking.domain.command.CrearRegistroEntradaCommand;
import com.iem.acceso_parking.domain.command.PagarTicketCommand;
import com.iem.acceso_parking.domain.model.RegistroEntrada;
import com.iem.acceso_parking.domain.query.ObtenerCochesQuery;
import com.iem.acceso_parking.domain.query.ObtenerCosteTicketQuery;

@Service
public class ParkingService implements ParkingServiceInputPort {

	@Autowired
	ContentManagerOutputPort contentManager;

	@Autowired
	ParkingRepositoryOutputPort parkingRepository;

	@Override
	public String crearRegistroEntrada(CrearRegistroEntradaCommand command) {

		String idImagen = contentManager.guardarImagen(command.getImagen());

		RegistroEntrada registro = RegistroEntrada.builder().fechaEntrada(LocalDateTime.now()).fechaPagado(null)
				.fechaSalida(null).id(null).idImagenContentManager(idImagen).matricula(command.getMatricula())
				.pagado(false).build();

		return parkingRepository.crearRegistro(registro);
	}

	@Override
	public List<String> obtenerCoches(ObtenerCochesQuery query) {

		return parkingRepository.obtenerCoches(query);
	}

	@Override
	public void pagarTicket(PagarTicketCommand command) throws Exception {

		Optional<RegistroEntrada> re = parkingRepository.obtenerRegistroEntrada(command.getId());

		if (re.isEmpty()) {
			throw new Exception("Error ticket");
		}
		
		

	}

	@Override
	public Float obtenerCosteTicket(ObtenerCosteTicketQuery query) throws Exception {

		Optional<RegistroEntrada> re = parkingRepository.obtenerRegistroEntrada(query.getId());

		if (re.isEmpty()) {
			throw new Exception("Error ticket");
		}

		float tarifaMinuto = 1.56f;

		long milisEntrada = Timestamp.valueOf(re.get().getFechaEntrada()).getTime();
		long milisAhora = Timestamp.valueOf(LocalDateTime.now()).getTime();

		long milisDentroParking = milisAhora - milisEntrada;

		float result = ((milisDentroParking / 1000) / 60) * tarifaMinuto;

		return result;
	}
}