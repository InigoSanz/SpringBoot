package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.jeasy.random.EasyRandom;

import jakarta.annotation.PostConstruct;

public class GestorPersonasServiceImpl implements GestorPersonasService {
	
	private static final EasyRandom ER = new EasyRandom();

	private static final int List = 0;

	private static final int Integer = 0;

	private List<Persona> bbdd;
	
	//private List<Integer> listaEdadPersonasVivienda;

	@PostConstruct
	public void postContruct() {
		bbdd = new ArrayList<>();
		
		// Vehiculo vehiculoRandom = ER.nextObject(Vehiculo.class);
		
		bbdd.addAll(ER.objects(Persona.class, 10).toList());
		bbdd.forEach(System.out::println);
	}

	@Override
	public List<Persona> obtenerBbdd() {
		return bbdd;
	}

	@Override
	public Persona obtenerPersonaPorDni(String dni) {
		return bbdd.stream()
				.filter(p -> p.getDni().equalsIgnoreCase(dni))
				.findFirst()
				.orElse(null);
	}

	@Override
	public List<Persona> obtenerPersonasPorEdad(int edad) {
		return bbdd.stream()
				.filter(p -> p.getEdad() == edad)
				.toList();
	}

	@Override
	public List<Persona> obtenerPersonasPorNombreApellido(String nombre, String apellido) {
		return bbdd.stream()
				.filter(persona -> persona.getNombre().equalsIgnoreCase(nombre)
						&& persona.getApellido().equalsIgnoreCase(apellido))
				.toList();
	}

	@Override
	public long cantidadPersonasPorEdad(int edad) {
		return bbdd.stream()
				.filter(p -> p.getEdad() == edad)
				.count();
	}

	@Override
	public List<Vehiculo> obtenerVehiculosPorDni(String dni) {
		return bbdd.stream()
				.filter(p -> p.getDni().equalsIgnoreCase(dni))
				.flatMap(p -> p.getVehiculos().stream())
				.toList();
	}

	@Override
	public List<Vivienda> obtenerViviendasPorDni(String dni) {
		return bbdd.stream()
				.filter(p -> p.getDni().equalsIgnoreCase(dni))
				.flatMap(p -> p.getViviendas().stream())
				.toList();
	}

	@Override
	public long cantidadVehiculosPorModelo(Modelos modelo) {
		return bbdd.stream()
				.flatMap(persona -> persona.getVehiculos().stream())
				.filter(vehiculo -> vehiculo.getModelo().equals(modelo))
				.count();
	}

	@Override
	public List<Persona> obtenerPersonasPorMatricula(String matricula) {
		return bbdd.stream()
				.filter(p -> p.getVehiculos().stream()
						.anyMatch(v -> v.getMatricula().equalsIgnoreCase(matricula)))
				.toList();
	}

	@Override
	public List<Persona> obtenerPersonasPorNumHabitaciones(int habitaciones) {
		return bbdd.stream()
				.filter(p -> p.getViviendas().stream()
						.anyMatch(v -> v.getHabitaciones() == habitaciones))
				.toList();
	}

	@Override
	public long cantidadPersonasPorNumHabitaciones(int habitaciones) {
		return bbdd.stream()
				.filter(p -> p.getViviendas().stream()
						.anyMatch(v -> v.getHabitaciones() == habitaciones))
				.count();
	}

	@Override
	public List<Persona> obtenerPersonasPorDireccion(String direccion) {
		return bbdd.stream()
				.filter(p -> p.getViviendas().stream()
						.anyMatch(v -> v.getDireccion().equalsIgnoreCase(direccion)))
				.toList();
	}

	@Override
	public List<String> obtenerDnisPorEdad(int edad) {
		return bbdd.stream()
				.filter(p -> p.getEdad() == edad)
				.map(p -> p.getDni())
				.toList();
	}

	@Override
	public String obtenerNombreApellidoPorMatricula(String matricula) {
		return bbdd.stream()
				.filter(p -> p.getVehiculos().stream()
						.anyMatch(v -> v.getMatricula().equalsIgnoreCase(matricula)))
				.map(p -> p.getNombre() + p.getApellido())
				.findFirst()
				.orElse(null);
	}

	@Override
	public String obtenerDniPropietarioPorDireccion(String direccion) {
		return bbdd.stream()
				.filter(p -> p.getViviendas().stream()
						.anyMatch(v ->
						v.getDireccion().equalsIgnoreCase(direccion)
						&& v.isPropietario()))
				.map(p -> p.getDni())
				.findFirst()
				.orElse(null);
	}
	
	
	@Override
	public int obtenerMediaEdadesPorDireccion(String direccion) {
		/*
		return List<Integer> listaEdadPersonasVivienda == bbdd.stream()
				.filter(p -> p.getViviendas().stream()
						.anyMatch(v -> v.getDireccion().equalsIgnoreCase(direccion)))
				.map(p -> p.getEdad()).toList();
		
		if (listaEdadPersonasVivienda.isEmpty()) {
			return 0;
		}
		
		int sumaTotal = listaEdadPersonasVivienda.stream()
				.reduce(0, (resultado, elemento) -> resultado + elemento);
		
		int cantidad = listaEdadPersonasVivienda.size();
		
		return sumaTotal / cantidad;
		*/
		return 0;
	}
	
}