package com.iem.jpa.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PERSON")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = true)
	private Integer age;
	
	// @OneToOne
	// @JoinColumn(name = "VEHICLE_ID", referencedColumnName = "ID")
	@ManyToMany
	@JoinTable(
			name = "PERSON_VEHICLES",
			joinColumns = @JoinColumn(name = "PERSON_ID"),
			inverseJoinColumns = @JoinColumn(name = "VEHICLE_ID")
			)
	private List<Vehicle> vehicle;

	public Person() {
		// Constructor por defecto
	}

	public Person(String name) {
		this.name = name;
	}

	public Person(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public Person(String name, Integer age, List<Vehicle> vehicles) {
		this.name = name;
		this.age = age;
		this.vehicle = vehicles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}