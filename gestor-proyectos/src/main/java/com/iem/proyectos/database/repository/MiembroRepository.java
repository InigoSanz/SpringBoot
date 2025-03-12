package com.iem.proyectos.database.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iem.proyectos.database.entity.MiembroEntity;

@Repository
public interface MiembroRepository extends MongoRepository<MiembroEntity, String>{
	
	List<MiembroEntity> findByIdProyectoAndIdTarea(String idProyecto, String idTarea);
}