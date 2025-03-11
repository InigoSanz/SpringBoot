package com.iem.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iem.mongo.entity.Articulo;

@Repository
public interface Repositorio extends MongoRepository<Articulo, String> {

}