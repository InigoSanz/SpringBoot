package com.iem.mongo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iem.mongo.entity.Articulo;
import com.iem.mongo.entity.Detalle;
import com.iem.mongo.entity.Dimensiones;
import com.iem.mongo.repository.Repositorio;

@SpringBootTest
class PruebasMongoApplicationTests {

	@Autowired
	Repositorio repositorio;

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

		repositorio.save(articulo);
	}
}