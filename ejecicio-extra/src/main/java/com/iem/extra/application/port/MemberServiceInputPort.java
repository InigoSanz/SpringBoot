package com.iem.extra.application.port;

import com.iem.extra.domain.model.Member;

public interface MemberServiceInputPort {
	
	public Member crearMiembro(String nombre, String dni);
}