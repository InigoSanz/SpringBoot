package com.iem.extra.application.port;

import java.util.Optional;

import com.iem.extra.domain.model.Member;

public interface MemberServiceInputPort {
	
	public Member crearMiembro(String nombre, String dni);

	Optional<Member> modificarMiembro(String memberId);

	Optional<Member> obtenerMiembro(String memberId);

	void modificarMiembro(Member member) throws Exception;
}