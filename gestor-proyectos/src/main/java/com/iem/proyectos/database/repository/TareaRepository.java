package com.iem.proyectos.database.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.iem.proyectos.database.entity.TareaEntity;

public interface TareaRepository extends MongoRepository<TareaEntity, String>{
	
	List<TareaEntity> findByIdProyecto(String idProyecto);
}