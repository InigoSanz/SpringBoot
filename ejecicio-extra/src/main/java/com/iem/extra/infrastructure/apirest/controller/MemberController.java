package com.iem.extra.infrastructure.apirest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iem.extra.application.port.MemberServiceInputPort;
import com.iem.extra.domain.model.Member;
import com.iem.extra.infrastructure.apirest.dto.PostMemberDto;
import com.iem.extra.infrastructure.apirest.dto.PutMemberDto;
import com.iem.extra.infrastructure.apirest.mapper.MemberToMemberDto;
import com.iem.extra.infrastructure.apirest.mapper.MemberToPutMemberDto;

@RestController
@RequestMapping("/members")
@SuppressWarnings("rawtypes")
public class MemberController {
	
	@Autowired
	MemberServiceInputPort service;
	
	@Autowired
	MemberToPutMemberDto mapper;
	
	@Autowired
	MemberToMemberDto mapper2;
	
	@PostMapping
	public ResponseEntity postMember(@RequestBody PostMemberDto dto) {
		
		// Mappear si es necesario
		
		Member miembro = service.crearMiembro(dto.getNombre(), dto.getDni());
		
		// Montar la URI con esto:
		miembro.getId();
		
		// return ResponseEntity.created(null).build();
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id-member}")
	public ResponseEntity putMember(
			@PathVariable("id-member") String id,
			@RequestBody PutMemberDto dto) {
		
		Member memberDomain = mapper.map(dto);
		memberDomain.setId(id);
		
		try {
			service.modificarMiembro(memberDomain);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id-member}")
	public ResponseEntity obtenerMiembro(@PathVariable("id-member") String id) {
		
		Optional<Member> memberDomain = service.obtenerMiembro(id);
		
		if (memberDomain.isPresent()) {
			return ResponseEntity.ok(mapper2.map(memberDomain.get()));
		}
		
		return ResponseEntity.noContent().build();
	}
}