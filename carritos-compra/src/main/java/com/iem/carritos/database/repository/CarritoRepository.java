package com.iem.carritos.database.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iem.carritos.database.entity.Carrito;
import com.iem.carritos.database.entity.EstadosCarrito;

@Repository
public interface CarritoRepository extends MongoRepository<Carrito, String> {

	List<Carrito> findByEstado(EstadosCarrito estado);

}