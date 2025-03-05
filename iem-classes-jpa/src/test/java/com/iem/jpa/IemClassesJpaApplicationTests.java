package com.iem.jpa;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.iem.jpa.entity.Person;
import com.iem.jpa.entity.Vehicle;
import com.iem.jpa.repository.PersonRepository;
import com.iem.jpa.repository.VehicleRepository;


@SpringBootTest
class IemClassesJpaApplicationTests {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@BeforeEach
	void antesDeCadaTest() {
		personRepository.deleteAll();
		vehicleRepository.deleteAll();
	}
	
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
	
	@Test
	void ejemplo() {
		List<Person> personaName = personRepository.findByName("Iñigo2");
		if (personaName != null) {
			System.out.println("Resultado nombres: " + personaName.get(0).getAge());
		}
		
		// En los optional hay que comprobar que no sea nulo, ¿con isPresent()?
		List<Person> personaAge = personRepository.findByAge(5);
		if (!personaAge.isEmpty()) {
			System.out.println("Resultado edades: " + personaAge.get(0).getAge());
		}
		
		List<Person> personaNameAge = personRepository.findByNameAndAge("Iñigo3", 5);
		if (!personaAge.isEmpty()) {
			System.out.println("Resultado mixto: " + personaNameAge.get(0).getName());
		}
	}
	
	@Transactional
	@Test
	void eliminar() {
		Long filasAfectadas = personRepository.deleteAllByName("Iñigo2");
		
		System.out.println(filasAfectadas);
		
		Assertions.assertTrue(personRepository.findByNameIgnoreCase("Iñigo2").isEmpty());
	}
	
	@Test
	void otracosa() {
		List<Person> personasPorEdad = personRepository.findByAgeOrderByNameAsc(5);
		personasPorEdad.forEach(persona -> System.out.println(persona.getName()));
	}
	
	@Test
	void conCoches() {
		for (int i = 0; i < 10; i++) {
			//personRepository.save(new Person("Nombre", 10, new Vehicle("Modelo", "Marca"))); No se puede sin haber creado antes algun vehiculo
			Vehicle salidaVehicle = vehicleRepository.save(new Vehicle("Cadet" + i, "Opel"));
			System.out.println("V: " + salidaVehicle.getId());
			
			Vehicle salidaVehicle2 = vehicleRepository.save(new Vehicle("SF90" + i, "Ferrari"));
			System.out.println("V: " + salidaVehicle2.getId());
			
			Person salidaPersona = personRepository.save(new Person("Nombre" + i, 10, List.of(salidaVehicle, salidaVehicle2)));
			System.out.println("P: " + salidaPersona.getId());
		}
	}
	
	
	
	
}