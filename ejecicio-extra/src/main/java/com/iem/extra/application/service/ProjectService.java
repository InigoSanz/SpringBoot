package com.iem.extra.application.service;

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
	public Member addMember(String proyectoId, String memberId) {
		return null;
	}
}