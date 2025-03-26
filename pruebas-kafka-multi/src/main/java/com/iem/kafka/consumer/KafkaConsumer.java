package com.iem.kafka.consumer;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.iem.kafka.dto.MessageDto;

@KafkaListener(topics = "${custom.topics.uno}", groupId = "${custom.groups.uno}", containerFactory = "messageContainerFactory")
@Component
public class KafkaConsumer {

	@KafkaHandler
	public void messageListener(MessageDto message) {
		System.out.println("Mensaje recibido: " + message.getMsg());
//		throw new Exception("");
	}

	@KafkaHandler
	public void messageListener(String message) {
		System.out.println("Mensaje recibido: " + message);
	}
}