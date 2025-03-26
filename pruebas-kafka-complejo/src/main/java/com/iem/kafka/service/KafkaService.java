package com.iem.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.iem.kafka.dto.MessageDto;
import com.iem.kafka.producer.KafkaProducer;

@Service
public class KafkaService {
	
	@Value("${custom.topics.uno}")
	private String topic1;
	
	@Autowired
	private KafkaProducer producer;
	
	public void lanzarMensajes() {
		int contador = 0;
		
		do {
			MessageDto message = new MessageDto();
			message.setMsg("Mensaje enviado");
			message.setUser("Usuario");
			producer.sendMessage(topic1, message);
			contador++;
		} while(contador < 10);
	}
}