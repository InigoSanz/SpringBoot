package com.demo.arq.infrastructure.database.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.demo.arq.infrastructure.database.entity.PeceraEntity;

@Repository
@EnableMongoRepositories
public interface PeceraRepository extends MongoRepository<PeceraEntity, String> {
	
	public Page<PeceraEntity> findByEliminado(boolean eliminado, Pageable pageable);
	
	public Optional<PeceraEntity> findByIdAndEliminado(String id, boolean eliminado);
}