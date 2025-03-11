package com.iem.mongo;

import java.util.List;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import com.iem.mongo.entity.Articulo;
import com.iem.mongo.entity.Detalle;
import com.iem.mongo.entity.Dimensiones;
import com.iem.mongo.repository.Repositorio;

@SpringBootTest
class PruebasMongoApplicationTests {
	
	private static final EasyRandom ER = new EasyRandom();
	
	@Autowired
	Repositorio repositorio;
	
	@Test
	void metodosPersonalizados() {
		repositorio.findByNombre("gNfZBdyFGRajVfJNonEnOinZj").forEach(System.out::println);
		
		repositorio.findByDimensionesAlto(666).forEach(System.out::println);
		
		repositorio.findByDetallesValor("UfzQhdgLLfDTDGspDb").forEach(System.out::println);
		
		repositorio.queryCustom("gNfZBdyFGRajVfJNonEnOinZj", 666, 1087885590).forEach(System.out::println);
		
		Articulo articuloEjemplo = new Articulo();
		articuloEjemplo.setNombre("gNfZBdyFGRajVfJNonEnOinZj");
		repositorio.findAll(Example.of(articuloEjemplo ));
	}
	
	@Test
	void testInsertar() {
		Articulo nuevoArticulo = ER.nextObject(Articulo.class);
		nuevoArticulo.setId(null);
		repositorio.save(nuevoArticulo);
	}
	
	@Test
	void testInsertarVarios() {
		List<Articulo> nuevoArticulo = ER.objects(Articulo.class, 10).toList();
		nuevoArticulo.stream().forEach(articulo -> articulo.setId(null));
		repositorio.saveAll(nuevoArticulo);
	}
	
	@Test
	void testActualizar() {
		List<Articulo> todosLosArticulos = repositorio.findAll();
		todosLosArticulos.stream().forEach(System.out::println);
		
		for (Articulo articulo : todosLosArticulos) {
			if (articulo.getDimensiones() != null) {
				articulo.getDimensiones().setAlto(666);
			} else {
				Dimensiones dim = new Dimensiones();
				dim.setAlto(888);
				articulo.setDimensiones(dim);
			}
				
		}
		
//		todosLosArticulos.get(0).getDimensiones().setAlto(666);
//		repositorio.save(todosLosArticulos.get(0));
		repositorio.saveAll(todosLosArticulos);
	}
	
	@Test
	void eliminarArticulo() {
		String id = "67d07cf7042889345c5ebbcd";
		repositorio.deleteById(id);
		
		List<Articulo> todos = repositorio.findAll();
		repositorio.delete(todos.get(0));
	}
	
	@Test
	void contextLoads() {
		repositorio.count();

		Articulo articulo = new Articulo();
		articulo.setNombre("Nombre");
		
		Dimensiones dimensiones = new Dimensiones();
		dimensiones.setAlto(0);
		articulo.setDimensiones(dimensiones);
		
		Detalle detalle1 = new Detalle();
		detalle1.setValor("valor1");
		
		Detalle detalle2 = new Detalle();
		detalle2.setValor("valor2");
		
		articulo.setDetalles(List.of(detalle1, detalle2));
		
//		articulo.setId("67d06d5716be4b54a6aea48c");
		
		repositorio.save(articulo);
	}
}