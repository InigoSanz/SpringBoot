package com.iem.acceso_parking.infrastructure.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iem.acceso_parking.application.port.ParkingProducerOutputPort;
import com.iem.acceso_parking.domain.model.RegistroEntrada;
import com.iem.acceso_parking.infrastructure.event.producer.RegistroEntradaProducer;

@Component
public class RegistroEntradaProducerService implements ParkingProducerOutputPort {
	
	@Autowired
	RegistroEntradaProducer entradaProducer;

	@Override
	public void send(RegistroEntrada registroEntrada) {
		
		entradaProducer.send(registroEntrada);	
	}
}