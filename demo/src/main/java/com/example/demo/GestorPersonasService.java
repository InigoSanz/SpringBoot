package com.example.demo;

import java.util.List;

public interface GestorPersonasService {
	
	List<Persona> obtenerBbdd();

	Persona obtenerPersonaPorDni(String dni);

	List<Persona> obtenerPersonasPorEdad(int edad);

	List<Persona> obtenerPersonasPorNombreApellido(String nombre, String apellido);

	long cantidadPersonasPorEdad(int edad);

	List<Vehiculo> obtenerVehiculosPorDni(String dni);

	List<Vivienda> obtenerViviendasPorDni(String dni);

	long cantidadVehiculosPorModelo(Modelos modelo);

	List<Persona> obtenerPersonasPorMatricula(String matricula);

	List<Persona> obtenerPersonasPorNumHabitaciones(int habitaciones);

	long cantidadPersonasPorNumHabitaciones(int habitaciones);

	List<Persona> obtenerPersonasPorDireccion(String direccion);

	List<String> obtenerDnisPorEdad(int edad);

	String obtenerNombreApellidoPorMatricula(String matricula);

	String obtenerDniPropietarioPorDireccion(String direccion);

	int obtenerMediaEdadesPorDireccion(String direccion);
}