package com.iem.acceso_parking.infrastructure.contentmanager.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.iem.acceso_parking.application.port.ContentManagerOutputPort;
import com.iem.acceso_parking.infrastructure.contentmanager.dto.ContentManagerInputDto;
import com.iem.acceso_parking.infrastructure.contentmanager.dto.ContentManagerOutputDto;
import com.iem.acceso_parking.infrastructure.contentmanager.dto.ContentManagerResultDto;

@Component
public class ContentManagerService implements ContentManagerOutputPort {

	private final RestTemplate restTemplate = new RestTemplate();

	@Override
	public String guardarImagen(byte[] imagen) {

		ResponseEntity<ContentManagerOutputDto> salida = restTemplate.exchange("http://hots_cm/guardar-archivo",
				HttpMethod.POST,
				new HttpEntity<ContentManagerInputDto>(ContentManagerInputDto.builder().archivo(imagen).build()),
				ContentManagerOutputDto.class);
		
		ContentManagerOutputDto body = salida.getBody();
		if (body != null) {
			return body.getId();
		}
		
		return null;
	}

	@Override
	public boolean comparaImagenes(String idImagenContentManager, byte[] imagenNueva) {
		
		ResponseEntity<ContentManagerResultDto> salida= restTemplate.exchange("http://hots_cm/guardar-archivo" + idImagenContentManager,
				HttpMethod.PUT,
				new HttpEntity<ContentManagerInputDto>(ContentManagerInputDto.builder().archivo(imagenNueva).build()),
				ContentManagerResultDto.class);
		
		ContentManagerResultDto body = salida.getBody();
		if (body != null) {
			return body.isResult();
		}
		
		return false;
	}
}