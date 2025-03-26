package com.iem.sucursal;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.iem.sucursal.dto.SucursalDto;

@Component
public class ConsumerSucursalTestService extends ConsumerLatch<SucursalDto> {

	@KafkaListener(topics = "${custom.topic.sucursal}", groupId = "${spring.kafka.consumer.group-id}-consumer-latch", containerFactory = "consumerFactory")
	public void receive(ConsumerRecord<String, SucursalDto> consumerRecord) {
		this.consumeMsg(consumerRecord);
	}
}