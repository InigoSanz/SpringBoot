package com.iem.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.iem.kafka.dto.MessageDto;

@Component
public class KafkaConsumer {
	
	@KafkaListener(
			topics = "${custom.topics.uno}",
			groupId = "${custom.groups.uno}",
			containerFactory = "messageContainerFactory"
			)
	public void messageListener(MessageDto message) {
		System.out.println("Mensaje recibido: " + message.getMsg());
//		throw new Exception("");
	}
	
	@KafkaListener(
			topics = "${custom.topics.uno}",
			groupId = "${custom.groups.dos}",
			containerFactory = "messageContainerFactory"
			)
	public void messageListener2(String message) {
		System.out.println("Mensaje 2 recibido: " + message);
	}
}