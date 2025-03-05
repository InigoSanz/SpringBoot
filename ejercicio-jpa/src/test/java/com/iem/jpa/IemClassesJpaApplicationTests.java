package com.iem.jpa;

import java.time.LocalDateTime;
import java.util.List;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.iem.jpa.entity.Access;
import com.iem.jpa.entity.Accions;
import com.iem.jpa.repository.AccessRepository;

@AutoConfigureMockMvc
@SpringBootTest
class IemClassesJpaApplicationTests {
	
	@Autowired 
	MockMvc mockMvc;
	
	@Autowired
	private AccessRepository accessRepository;
	
	@BeforeEach
	void antesDeCadaTest() {
		accessRepository.deleteAll();
	}
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void getAllAccessTest() throws Exception {
		Access access = new Access();
		access.setAccion(Accions.SALIDA);
		access.setFecha(LocalDateTime.now());
		access.setMatricula("11111");
		// Preparar Datos En BBDD, si toca
		accessRepository.save(access);
		
		Access access2 = new Access();
		access2.setAccion(Accions.SALIDA);
		access2.setFecha(LocalDateTime.now());
		access2.setMatricula("22222");
		// Preparar Datos En BBDD, si toca
		accessRepository.save(access2);
		
		// Preparar Datos Entrada, si toca
		
		// Lanzar la prueba
		mockMvc.perform(MockMvcRequestBuilders
				.get("/access")
				.accept(MediaType.APPLICATION_JSON)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				)
		.andExpect(MockMvcResultMatchers.status().is(200))
//		.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("\"matricula\":"\"""))) Poniendolo bien seria para comprobar los datos que se pasan
		.andDo(MockMvcResultHandlers.print());
		// Validar datos
	}
	
	@Test
	void postAccessTest() throws Exception {
		Access access = new Access();
		access.setAccion(Accions.SALIDA);
		access.setFecha(LocalDateTime.now());
		access.setMatricula("11111");
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/access")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"matricula\":\"11111\", \"accion\":\"SALIDA\"}")
				.accept(MediaType.APPLICATION_JSON)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				)
		.andExpect(MockMvcResultMatchers.status().is(204))
//		.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("\"matricula\":"\"""))) Poniendolo bien seria para comprobar los datos que se pasan
		.andDo(MockMvcResultHandlers.print());
		
		// Validar datos
		List<Access> enBBDD = accessRepository.findAll();
		Assertions.assertFalse(enBBDD.isEmpty());
		Assertions.assertTrue(enBBDD.get(0).getMatricula().equalsIgnoreCase("11111"));
	}
}