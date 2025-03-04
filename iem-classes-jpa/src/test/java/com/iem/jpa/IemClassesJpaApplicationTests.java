package com.iem.jpa;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iem.jpa.entity.Person;
import com.iem.jpa.repository.PersonRepository;

@SpringBootTest
class IemClassesJpaApplicationTests {

	@Autowired
	PersonRepository personRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void contar() {
		System.out.println(personRepository.count());
		for (int i = 0; i < 10; i++) {
			System.out.println(personRepository.save(new Person("Iñigo" + i, i)));
		}
		System.out.println(personRepository.count());
		
		List<Person> personas = personRepository.findAll();
		if (!personas.isEmpty()) {
			personas.get(0).setAge(90);
			System.out.println(personas.get(0).getId());
			personRepository.save(personas.get(0));
		}
		
		Optional<Person> persona = personRepository.findById(1L);
		if (persona.isPresent()) {
			System.out.println("edad:" + persona.get().getAge());
		}
		
		personRepository.deleteById(2L);
		System.out.println(personRepository.count());
	}	
}