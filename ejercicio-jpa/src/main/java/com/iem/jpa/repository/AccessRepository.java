package com.iem.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iem.jpa.entity.Access;
import com.iem.jpa.entity.Accions;

@Repository
public interface AccessRepository extends JpaRepository<Access, Long> {
	
	List<Access> findByMatriculaAndAccionOrderByFechaDesc(String matricula, Accions accion);
}