package com.iem.kafka.producer;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessageAsync(String topic, String message) {
		kafkaTemplate.send(topic, message);
	}

	public void sendMessage(String topic, String message) {
		CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
		
		future.whenComplete((result, ex) -> {
			if (ex == null) {
				System.out.println("Ha ido bien " + result);
			} else {
				System.out.println("Ha ido mal " + ex.getMessage());
			}
		});
	}
}