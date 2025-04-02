package com.iem.extra.infrastructure.database.mapper;

import org.mapstruct.Mapper;

import com.iem.extra.domain.model.Project;
import com.iem.extra.infrastructure.database.entity.ProjectEntity;

@Mapper(componentModel = "Spring")
public interface ProjectToProjectEntityMapper {

	public ProjectEntity map(Project proyecto);

}