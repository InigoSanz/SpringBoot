package com.iem.sucursal.producer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
public class SucursalProducer {

	private static final Logger log = LoggerFactory.getLogger(SucursalProducer.class);

	@Value("${spring.kafka.timeout:6000}")
	private long kafkaTimeout;

	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;

	public void sendMessage(String topic, Object msg) {
		log.info("Mensaje producido");

		try {
			kafkaTemplate.send(topic, msg).get(kafkaTimeout, TimeUnit.SECONDS);
			handlerSucces(msg);
		} catch (InterruptedException e) {
			handlerInterruptedException(msg, e);
			Thread.currentThread().interrupt();
		} catch (ExecutionException e) {
			handlerExecutionException(msg, e);
		} catch (TimeoutException e) {
			handlerTimeoutException(msg, e);
		}

	}

	public void sendMessageAsyn(String topic, Object msg) {
		log.info("Mensaje producido Asincrono");

		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, msg);
		future.handle((result, ex) -> {
			if (ex != null) {
				if (ex instanceof TimeoutException e) {
					handlerTimeoutException(msg, e);
				} else if (ex instanceof ExecutionException e) {
					handlerExecutionException(msg, e);
				} else if (ex instanceof InterruptedException e) {
					handlerInterruptedException(msg, e);
					Thread.currentThread().interrupt();
				}
			} else {
				handlerSucces(msg);
			}
			return result;
		});
	}

	private void handlerTimeoutException(Object msg, TimeoutException e) {
		log.error("Mensaje TimeoutException: " + msg.toString(), e);
	}

	private void handlerExecutionException(Object msg, ExecutionException e) {
		log.error("Mensaje ExecutionException: " + msg.toString(), e);
	}

	private void handlerInterruptedException(Object msg, InterruptedException e) {
		log.error("Mensaje InterruptedException: " + msg.toString(), e);
	}

	private void handlerSucces(Object msg) {
		log.info("Mensaje enviado correctamente: " + msg.toString());
	}
}