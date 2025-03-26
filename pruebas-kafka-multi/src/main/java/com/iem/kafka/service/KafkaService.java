package com.iem.kafka.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.iem.kafka.dto.MessageDto;
import com.iem.kafka.dto.NewMessageDto;
import com.iem.kafka.producer.KafkaProducer;

@Service
public class KafkaService {

	private static final String[] USUARIOS = { "Manolo", "Benito" };

	@Value("${custom.topics.uno}")
	private String topic1;

	@Autowired
	private KafkaProducer producer;

//	private int contador = 0;
	private Random random = new Random();

	public void lanzarMensajes() {
		int contador = 0;

		do {
			Object message = crearMensajeRandom();
			producer.sendMessage(topic1, message);
			contador++;
		} while (contador < 10);
	}

	private Object crearMensajeRandom() {
		Object message;
		switch (random.nextInt(3)) {
		case 1:
			NewMessageDto men = new NewMessageDto();
			men.setMsg("New Message Dto");
			men.setUser(getRandomUser());
			message = men;
			break;
		case 2:
			MessageDto men = new MessageDto();
			men.setMsg("Mensaje DTO");
			men.setUser(getRandomUser());
			message = men;
			break;
		case 3:
			message = "Simple String: " + getRandomUser();
			break;
		default:
			break;
		}

		MessageDto message = new MessageDto();
		message.setMsg("Mensaje enviado");
		message.setUser("Usuario");
		return null;
	}

	private String getRandomUser() {
		int randomIndice = random.nextInt(0, USUARIOS.length);
		return USUARIOS[randomIndice];
	}
}