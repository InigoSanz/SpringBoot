package com.demo.arq.infrastructure.apirest.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.arq.application.port.input.PeceraServiceInputPort;
import com.demo.arq.domain.exception.BusinessException;
import com.demo.arq.domain.model.Pecera;
import com.demo.arq.infrastructure.apirest.dto.request.PatchPeceraDto;
import com.demo.arq.infrastructure.apirest.dto.request.PostPutPeceraDto;
import com.demo.arq.infrastructure.apirest.mapper.PeceraToPatchPeceraDtoMapper;
import com.demo.arq.infrastructure.apirest.mapper.PeceraToPeceraDtoMapper;
import com.demo.arq.infrastructure.apirest.mapper.PeceraToPostPutPeceraDtoMapper;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/peceras")
@CrossOrigin(origins = "localhost:4200")
@SuppressWarnings("rawtypes")
public class PeceraController {
	
	@Autowired
	PeceraServiceInputPort peceraService;
	
	@Autowired
	PeceraToPostPutPeceraDtoMapper peceraToPostPutPeceraDtoMapper;
	
	@Autowired
	PeceraToPatchPeceraDtoMapper peceraToPatchPeceraDtoMapper;
	
	@Autowired
	PeceraToPeceraDtoMapper peceraToPeceraDtoMapper;
	
	@GetMapping
	public ResponseEntity getPeceras(Pageable pageable) {
		log.debug("getPeceras");
		
		Page<Pecera> pageDomain;
		try {
			pageDomain = peceraService.obtenerPeceras(pageable);
		} catch (BusinessException e) {
			log.error("Error obteniendo peceras", e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		if (pageDomain == null || pageDomain.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(peceraToPeceraDtoMapper.fromDomainToDto(pageDomain));
	}
	
	@GetMapping("/{pecera-id}")
	public ResponseEntity getPecera(@Valid @PathVariable("pecera-id") String id) {
		log.debug("getPecera");
		
		Optional<Pecera> domain = peceraService.obtenerPecera(id);
		
		if (domain.isPresent()) {
			return ResponseEntity.ok(peceraToPeceraDtoMapper.fromDomainToDto(domain.get()));
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping
	public ResponseEntity postPecera(@Valid @RequestBody PostPutPeceraDto dto) {
		log.debug("postPecera");
		
		Pecera domain = peceraToPostPutPeceraDtoMapper.fromDtoToDomain(dto);
		
		String idNewPecera = peceraService.crearPecera(domain);
		
		URI locationHeader = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(idNewPecera)
				.toUri();
		
		return ResponseEntity.created(locationHeader).build();
	}
	
	@PatchMapping("/{pecera-id}")
	public ResponseEntity patchPecera(@Valid @PathVariable("pecera-id") String id, @Valid @RequestBody PatchPeceraDto dto) {
		log.debug("patchPecera");
		
		Pecera domain = peceraToPatchPeceraDtoMapper.fromDtoToDomain(dto);
		domain.setId(id);
		
		try {
			peceraService.modificacionParcialPecera(domain);
		} catch (BusinessException e) {
			log.error("Error modificando pecera", e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{pecera-id}")
	public ResponseEntity putPecera(@Valid @PathVariable("pecera-id") String id, @Valid @RequestBody PostPutPeceraDto dto) {
		log.debug("putPecera");
		
		Pecera domain = peceraToPostPutPeceraDtoMapper.fromDtoToDomain(dto);
		domain.setId(id);
		
		try {
			peceraService.modificacionTotalPecera(domain);
		} catch (BusinessException e) {
			log.error("Error modificando pecera", e);
			return ResponseEntity.badRequest().body(e.getMessage());			
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{pecera-id}")
	public ResponseEntity deletePecera(@Valid @PathVariable("pecera-id") String id) {
		log.debug("deletePecera");
		
		try {
			peceraService.eliminarPecera(id);
		} catch (BusinessException e) {
			log.error("Error eliminado pecera", e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.noContent().build();
	}
	
}