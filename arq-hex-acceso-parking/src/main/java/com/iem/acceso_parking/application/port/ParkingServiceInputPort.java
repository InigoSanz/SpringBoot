package com.iem.acceso_parking.application.port;

import java.util.List;

import com.iem.acceso_parking.domain.command.CrearRegistroEntradaCommand;
import com.iem.acceso_parking.domain.command.PagarTicketCommand;
import com.iem.acceso_parking.domain.command.ValidarSalidaCommand;
import com.iem.acceso_parking.domain.query.ObtenerCochesQuery;
import com.iem.acceso_parking.domain.query.ObtenerCosteTicketQuery;

public interface ParkingServiceInputPort {
	
	public String crearRegistroEntrada(CrearRegistroEntradaCommand command);

	public List<String> obtenerCoches(ObtenerCochesQuery query);
	
	public void pagarTicket(PagarTicketCommand command) throws Exception;
	
	public Float obtenerCosteTicket(ObtenerCosteTicketQuery query) throws Exception;
	
	public boolean validarSalida(ValidarSalidaCommand command) throws Exception;
}