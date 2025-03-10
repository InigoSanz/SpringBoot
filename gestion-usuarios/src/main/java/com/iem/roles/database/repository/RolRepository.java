package com.iem.roles.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iem.roles.database.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
	
	List<Rol> findByIdUsuario(Long idUsuario);
}