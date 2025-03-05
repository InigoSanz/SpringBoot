package com.iem.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iem.jpa.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
	// Te hace la QUERY automaticamente
	List<Person> findByName(String name);
	
	// Esta es igual que la query nativa de abajo
	List<Person> findByNameIgnoreCase(String name);
	
	// Te hace la QUERY automaticamente
	List<Person> findByAge(Integer age);
	
	List<Person> findByNameAndAge(String name, Integer age);
	
	@Query(value = "SELECT * FROM PERSON WHERE LOWER(NAME) = LOWER(:name)", nativeQuery = true)
	List<Person> buscarPorNombre(@Param("name") String name);
	
	Long deleteAllByName(String name);
	
	List<Person> findByAgeOrderByNameAsc(Integer age);
	
	List<Person> findByNameOrderByAgeDesc(String name);
}