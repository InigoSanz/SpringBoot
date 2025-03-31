package com.iem.mock.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iem.mock.entity.PenaEntity;

@Repository
public interface PenaRepository extends MongoRepository<PenaEntity, Long> {
}