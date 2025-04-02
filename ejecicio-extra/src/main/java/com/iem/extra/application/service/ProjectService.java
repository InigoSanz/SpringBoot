package com.iem.extra.application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.extra.application.port.ProjectRepositoryOutputPort;
import com.iem.extra.application.port.ProjectServiceInputPort;
import com.iem.extra.domain.model.Member;
import com.iem.extra.domain.model.Project;

@Service
public class ProjectService implements ProjectServiceInputPort {

	@Autowired
	ProjectRepositoryOutputPort projectRepositoryOutputPort;
	
	@Autowired
	MemberService memberService;

	@Override
	public Project crearProyecto(String nombre) {
		
		Project p = new Project();
		p.setNombre(nombre);
		// Más datos por defecto si queremos

		String id = projectRepositoryOutputPort.crearProjecto(p);
		p.setId(id);

		return p;
	}

	@Override
	public Member addMember(String proyectoId, String memberId) throws Exception {
		
		Optional<Member> miembro = memberService.obtenerMiembro(memberId);
		if (miembro.isEmpty()) {
			throw new Exception("Miembro no existe");
		}
		
		miembro.get().setProyectId(proyectoId);
		
		memberService.modificarMiembro(miembro.get());
		
		return null;
	}
}