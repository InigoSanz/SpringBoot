package com.iem.proyectos.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iem.proyectos.database.entity.ProyectoEntity;

@Repository
public interface ProyectoRepository extends MongoRepository<ProyectoEntity, String> {
	
	
}