package com.iem.roles.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iem.roles.database.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{

}