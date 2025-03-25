package com.iem.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.iem.kafka.producer.KafkaProducer;


@SpringBootTest
class PruebasKafkaApplicationTests {
	
	@Value("${custom.topics.uno}")
	private String topic1;
	
	@Autowired
	KafkaProducer producer;
	
	@Test
	void contextLoads() {
		producer.sendMessageAsync(topic1, "Mensaje enviado a kafka");
	}
}