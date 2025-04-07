package com.iem.acceso_parking.application.port;

import com.iem.acceso_parking.domain.model.RegistroEntrada;

public interface ParkingProducerOutputPort {

	void send(RegistroEntrada registroEntrada);
}