package com.iem.acceso_parking.infrastructure.database.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iem.acceso_parking.infrastructure.database.entity.RegistroEntradaEntity;

@Repository
public interface RegistroEntradaRepository extends MongoRepository<RegistroEntradaEntity, String> {
	
	List<RegistroEntradaEntity> findByFechaSalida(LocalDateTime fechaSalida);
	
	List<RegistroEntradaEntity> findByMatriculaAndFechaSalida(String matricula, LocalDateTime fechaSalida);
}