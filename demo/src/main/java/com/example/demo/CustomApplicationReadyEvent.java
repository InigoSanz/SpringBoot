package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class CustomApplicationReadyEvent implements ApplicationListener<ApplicationReadyEvent> {

	@Value("${nuestra.propiedad.string}")
	private String nuestraPropiedadString;

	@Value("${nuestra.propiedad.int}")
	private String nuestraPropiedadInt;
	
	@Autowired
	GestorPersonasService gestorPersonasService;

	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		System.out.println("onApplicationEvent");

		System.out.println("nuestraPropiedadString " + nuestraPropiedadString);
		System.out.println("nuestraPropiedadInt " + nuestraPropiedadInt);

		// System.getenv("Path");
		// TODO: Cosas que tenemos pendientes por hacer en el código

		List<Vehiculo> vehiculos = new ArrayList<>();
		vehiculos.add(new Vehiculo("1111AAA", Modelos.BMW, 10000, false));
		vehiculos.add(new Vehiculo("2222BBB", Modelos.AUDI, 50000, true));
		vehiculos.add(new Vehiculo("3333CCC", Modelos.HONDA, 70000, false));

		List<Vehiculo> vehiculosSoloAudi = vehiculos.stream().filter(v -> v.getModelo().equals(Modelos.AUDI)).toList();
		vehiculosSoloAudi.forEach(System.out::println);

		// System.out.println(vehiculosSoloAudi);

		List<String> matriculas = vehiculos.stream().map(vehiculo -> vehiculo.getMatricula()).toList();
		matriculas.forEach(System.out::println);

		List<Vehiculo> ordenadosPorKm = vehiculos.stream()
				.sorted((v1, v2) -> Integer.compare(v1.getKilometros(), v2.getKilometros()))
				.collect(Collectors.toList());
		System.out.println(ordenadosPorKm);

		int kmTotales = vehiculos.stream().map(vehiculo -> vehiculo.getKilometros()).reduce(0,
				(acumulado, elementoActual) -> acumulado + elementoActual);
		System.out.println(kmTotales);

	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("postConstruct");
	}

	@Scheduled(fixedDelay = 1000L)
	public void doSomething() {
		System.out.println("doSomething @Scheduled");
	}
}