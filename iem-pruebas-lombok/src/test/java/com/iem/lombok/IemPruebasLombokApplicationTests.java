package com.iem.lombok;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class IemPruebasLombokApplicationTests {

	@Test
	void contextLoads() {
		
		log.debug("debug");
		log.info("info");
		ObjectoPruebas obj = new ObjectoPruebas();
		ObjectoPruebas obj2 = new ObjectoPruebas("String", 10.0f, 99, false);
		
		System.out.println(obj2);
		
		System.out.println(ObjectoPruebas.builder().propiedad1("Lo que sea").propiedad2(0).build());
		
	}

}
