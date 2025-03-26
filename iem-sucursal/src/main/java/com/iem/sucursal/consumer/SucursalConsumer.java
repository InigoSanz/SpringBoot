package com.iem.sucursal.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.iem.sucursal.dto.NuevoCocheInputEventDto;
import com.iem.sucursal.service.SucursalService;

@Component
public class SucursalConsumer {
	
	private static final Logger log = LoggerFactory.getLogger(SucursalConsumer.class);
	
	@Autowired
	SucursalService sucursalService;
	
	@KafkaListener(
			topics = "${custom.topic.nuevo-coche}",
			groupId = "${spring.kafka.consumer.group-id}",
			containerFactory = "consumerFactory"
			)
	public void receive(ConsumerRecord<String, NuevoCocheInputEventDto> consumerRecord) {
		log.info("Mensaje consumido");
		
		sucursalService.nuevoCoche(consumerRecord.value().getDatosNuevoCoche());
		
	}
}