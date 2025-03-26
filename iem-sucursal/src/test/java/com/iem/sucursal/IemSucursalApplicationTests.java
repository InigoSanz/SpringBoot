package com.iem.sucursal;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

import com.iem.sucursal.dto.NuevoCocheInputEventDto;
import com.iem.sucursal.producer.SucursalProducer;
import com.iem.sucursal.service.SucursalService;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9095", "port=9095" })
class IemSucursalApplicationTests {

	private CountDownLatch latch = new CountDownLatch(1);

	@Value("${custom.topic.sucursal}")
	private String topicSucursal;

	@Value("${custom.topic.nuevo-coche}")
	private String topicNuevoCoche;

	@Autowired
	SucursalProducer producer;

	@Autowired
	SucursalService sucursalService;

	@Test
	void nuevoCoche() throws InterruptedException {
		NuevoCocheInputEventDto nuevoCoche = new NuevoCocheInputEventDto();
		nuevoCoche.setId("idNuevoCoche");
		nuevoCoche.setDatosNuevoCoche("Datos del nuevo coche");

		producer.sendMessage(topicNuevoCoche, nuevoCoche);
		latch.await(3, TimeUnit.SECONDS);
		
		Assertions.assertFalse(sucursalService.info.isEmpty());
		Assertions.assertTrue(sucursalService.info.contains(nuevoCoche.getDatosNuevoCoche()));
	}
}