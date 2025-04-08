package com.iem.acceso_parking.infrastructure.event.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RegistroEntradaProducer {
	
	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;
	
	public void send(Object mensaje) {
		kafkaTemplate.send("topicoKafka", mensaje);
	}
}