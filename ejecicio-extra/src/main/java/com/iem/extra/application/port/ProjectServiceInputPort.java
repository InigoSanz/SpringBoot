package com.iem.extra.application.port;

import com.iem.extra.domain.model.Member;
import com.iem.extra.domain.model.Project;

public interface ProjectServiceInputPort {

	public Project crearProyecto(String nombre);
	
	public Member addMember(String proyectoId, String memberId) throws Exception;
}