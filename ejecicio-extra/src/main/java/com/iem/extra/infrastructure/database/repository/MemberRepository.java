package com.iem.extra.infrastructure.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.iem.extra.infrastructure.database.entity.MemberEntity;

@EnableMongoRepositories
@Repository
public interface MemberRepository extends MongoRepository<MemberEntity, String> {

}