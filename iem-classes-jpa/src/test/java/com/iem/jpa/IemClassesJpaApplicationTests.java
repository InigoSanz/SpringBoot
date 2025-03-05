package com.iem.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iem.jpa.repository.AccessRepository;

@SpringBootTest
class IemClassesJpaApplicationTests {

	@Autowired
	private AccessRepository accessRepository;
	
	@BeforeEach
	void antesDeCadaTest() {
		accessRepository.deleteAll();
	}
	
	@Test
	void contextLoads() {
	}	
}