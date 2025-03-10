package com.iem.roles.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iem.roles.database.entity.Permiso;
import com.iem.roles.database.entity.PermisoId;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, PermisoId> {

}