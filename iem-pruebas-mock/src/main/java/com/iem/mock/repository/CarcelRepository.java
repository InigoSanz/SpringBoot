package com.iem.mock.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iem.mock.entity.CarcelEntity;

@Repository
public interface CarcelRepository extends MongoRepository<CarcelEntity, Long> {
}