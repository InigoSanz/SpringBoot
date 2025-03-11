package com.iem.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.iem.mongo.entity.Articulo;
import com.iem.mongo.entity.Detalle;

@Repository
public interface Repositorio extends MongoRepository<Articulo, String> {
	
	List<Articulo> findByNombre(String nombre);
	
	List<Articulo> findByDimensionesAlto(int alto);
	
//	List<Articulo> findByDetallesValor(String valor);
	
	List<Detalle> findByDetallesValor(String valor);
	
	@Query("{ 'nombre': ?0, 'dimensiones.alto': ?1, 'dimensiones.ancho': ?2 }")
	List<Articulo> queryCustom(String nombre, int alto, int ancho);
}