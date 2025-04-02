package com.iem.extra.infrastructure.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iem.extra.application.port.ProjectRepositoryOutputPort;
import com.iem.extra.domain.model.Project;
import com.iem.extra.infrastructure.database.entity.ProjectEntity;
import com.iem.extra.infrastructure.database.mapper.ProjectToProjectEntityMapper;
import com.iem.extra.infrastructure.database.repository.ProjectRepository;

@Component
public class ProjectRepositoryService implements ProjectRepositoryOutputPort {
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	ProjectToProjectEntityMapper projectToProjectEntityMapper;
	
	@Override
	public String crearProjecto(Project proyecto) {
		ProjectEntity pe = projectToProjectEntityMapper.map(proyecto);
		
		ProjectEntity nuevoProyecto = projectRepository.save(pe);
		
		return nuevoProyecto.getId();
	}
}