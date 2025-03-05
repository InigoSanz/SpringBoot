package com.iem.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iem.jpa.entity.Access;

@Repository
public interface AccessRepository extends JpaRepository<Access, Long> {

}