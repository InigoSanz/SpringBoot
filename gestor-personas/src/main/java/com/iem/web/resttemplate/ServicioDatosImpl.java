package com.iem.web.resttemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Qualifier("RestTemplate")
public class ServicioDatosImpl implements ServicioDatos {

	private static final Logger log = LoggerFactory.getLogger(ServicioDatosImpl.class);

	private static final String HOST = "https://httpbin.org";
	private static final String URL_GET = HOST + "/get";
	private static final String URL_POST = HOST + "/post";
	private static final String URL_PUT = HOST + "/put";
	private static final String URL_DEL = HOST + "/delete";

	private final RestTemplate restTemplate;

	public ServicioDatosImpl() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public String postDato(Dato dato) {

		ResponseEntity<String> salida = restTemplate.exchange(URL_POST, HttpMethod.POST, new HttpEntity<Dato>(dato),
				String.class);

		if (salida.getStatusCode().is2xxSuccessful()) {
			log.debug(salida.getBody());
			return salida.getBody();
		}

		return null;
	}

	@Override
	public String getDato() {

		ResponseEntity<String> salida = restTemplate.exchange(URL_GET, HttpMethod.GET, null, String.class);

		if (salida.getStatusCode().is2xxSuccessful()) {
			log.debug(salida.getBody());
			return salida.getBody();
		}

		return null;
	}

	@Override
	public String putDato(Dato dato) {
		ResponseEntity<String> salida = restTemplate.exchange(URL_PUT, HttpMethod.PUT, new HttpEntity<Dato>(dato),
				String.class);

		if (salida.getStatusCode().is2xxSuccessful()) {
			log.debug(salida.getBody());
			return salida.getBody();
		}

		return null;
	}

	@Override
	public String deleteDato() {
		ResponseEntity<String> salida = restTemplate.exchange(URL_DEL, HttpMethod.DELETE, null, String.class);

		if (salida.getStatusCode().is2xxSuccessful()) {
			log.debug(salida.getBody());
			return salida.getBody();
		}

		return null;
	}
}