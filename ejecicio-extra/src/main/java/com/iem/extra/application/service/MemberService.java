package com.iem.extra.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.extra.application.port.MemberRepositoryOutputPort;
import com.iem.extra.application.port.MemberServiceInputPort;
import com.iem.extra.domain.model.Member;

@Service
public class MemberService implements MemberServiceInputPort {

	@Autowired
	MemberRepositoryOutputPort memberRepositoryOutputPort;

	@Override
	public Member crearMiembro(String nombre, String dni) {
		Member m = new Member();
		m.setNombre(nombre);
		m.setDni(dni);
		// Más datos por defecto si queremos

		String id = memberRepositoryOutputPort.crearMiembro(m);
		m.setId(id);

		return m;
	}
}