package com.iem.web.resttemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Qualifier("webClient")
public class ServicioDatosImpl2 implements ServicioDatos {

	private static final Logger log = LoggerFactory.getLogger(ServicioDatosImpl2.class);

	private static final String URL_GET = "/get";
	private static final String URL_POST = "/post";
	private static final String URL_PUT = "/put";
	private static final String URL_DEL = "/delete";
	
	@Value("${host.httpbin}")
	private String host;
	
	private final RestTemplate restTemplate;

	public ServicioDatosImpl2() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public String postDato(Dato dato) {

		ResponseEntity<String> salida = restTemplate.exchange(host + URL_POST, HttpMethod.POST, new HttpEntity<Dato>(dato),
				String.class);

		if (salida.getStatusCode().is2xxSuccessful()) {
			log.debug(salida.getBody());
			return salida.getBody();
		}

		return null;
	}

	@Override
	public String getDato() {

		ResponseEntity<String> salida = restTemplate.exchange(host + URL_GET, HttpMethod.GET, null, String.class);

		if (salida.getStatusCode().is2xxSuccessful()) {
			log.debug(salida.getBody());
			return salida.getBody();
		}

		return null;
	}

	@Override
	public String putDato(Dato dato) {
		ResponseEntity<String> salida = restTemplate.exchange(host + URL_PUT, HttpMethod.PUT, new HttpEntity<Dato>(dato),
				String.class);

		if (salida.getStatusCode().is2xxSuccessful()) {
			log.debug(salida.getBody());
			return salida.getBody();
		}

		return null;
	}

	@Override
	public String deleteDato() {
		ResponseEntity<String> salida = restTemplate.exchange(host + URL_DEL, HttpMethod.DELETE, null, String.class);

		if (salida.getStatusCode().is2xxSuccessful()) {
			log.debug(salida.getBody());
			return salida.getBody();
		}

		return null;
	}
}