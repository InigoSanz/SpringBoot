package com.iem.acceso_parking.infrastructure.police.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.iem.acceso_parking.application.port.PoliceOutputPort;

@Component
public class PoliceService implements PoliceOutputPort {

	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public void call() {
		
		restTemplate.exchange("http://host_police/call", HttpMethod.POST, HttpEntity.EMPTY, String.class);
	}
}