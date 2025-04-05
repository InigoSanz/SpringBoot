package com.iem.gestion_empleado.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iem.gestion_empleado.database.entity.DepartamentoEntity;

@Repository
public interface DepartamentoRepository extends MongoRepository<DepartamentoEntity, String> {

}