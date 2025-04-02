package com.iem.extra.application.port;

import java.util.Optional;

import com.iem.extra.domain.model.Member;

public interface MemberRepositoryOutputPort {

	public String crearMiembro(Member member);

	public Optional<Member> obtenerMiembro(String memberId);

	public void modificarMiembro(Member member);
}