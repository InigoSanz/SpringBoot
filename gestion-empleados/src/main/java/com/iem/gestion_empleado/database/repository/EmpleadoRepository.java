package com.iem.gestion_empleado.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iem.gestion_empleado.database.entity.EmpleadoEntity;

@Repository
public interface EmpleadoRepository extends MongoRepository<EmpleadoEntity, String> {

}