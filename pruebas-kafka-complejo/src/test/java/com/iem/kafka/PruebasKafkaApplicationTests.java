package com.iem.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iem.kafka.service.KafkaService;


@SpringBootTest
class PruebasKafkaApplicationTests {
	
	@Autowired
	KafkaService service;
	
	@Test
	void contextLoads() {
		service.lanzarMensajes();
	}
}