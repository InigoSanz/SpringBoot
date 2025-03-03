package web.clases.iem.controller;

import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import web.clases.iem.dto.Coche;

@RestController
@RequestMapping("/coches")
public class Controlador {
	
	@Value("${tarifa}")
	private Float tarifaSegundos;
	
	private List<Coche> coches = new ArrayList<>();
	
	@GetMapping
	public ResponseEntity<List<Coche>> obtenerVehiculos() {
		
		return ResponseEntity.ok(coches);
	}
	
	@PostMapping
	public ResponseEntity<Void> entradaCoche(@RequestBody Coche coche) {
		
		String idCoche = UUID.randomUUID().toString();	
		
		coche.setId(idCoche);
		coches.add(coche);
		coche.setFechaEntrada(LocalDateTime.now());
		
		return ResponseEntity.created(crearUri(idCoche)).build();	
	}
	
	@GetMapping("/{id-coche}/calculo")
	public ResponseEntity<Float> calcularCobro(@PathVariable("id-coche") String idCoche) {
		Float calculo = 0.0F;
		
		for (Coche coche : coches) {
			if (coche.getId().equalsIgnoreCase(idCoche)) {
				long milisAntes = Timestamp.valueOf(coche.getFechaEntrada()).getTime();
				long milisAhora = Timestamp.valueOf(LocalDateTime.now()).getTime();
				long totalTiempoDentro = milisAhora - milisAntes;
				calculo = (totalTiempoDentro / 1000) * tarifaSegundos;
				break;
			}
		}
		
		return ResponseEntity.ok(calculo );
	}
	
	@PatchMapping
	public ResponseEntity<Void> salidaCoche(@RequestBody Coche coche) {
		for (Coche cocheBusqueda : coches) {
			if (cocheBusqueda.getId().equalsIgnoreCase(coche.getId())) {
				cocheBusqueda.setFechaSalida(LocalDateTime.now());
				break;
			}
		}
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id-coche}")
	public ResponseEntity<Void> eliminarVehiculo(@PathVariable("id-coche") String idCoche) {
		for (Coche cocheBusqueda : coches) {
			if (cocheBusqueda.getId().equalsIgnoreCase(idCoche)) {
				cocheBusqueda.setBorrado(true);
				break;
			}
		}
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Método para generar URIs.
	 * 
	 * @param id
	 * @return URI
	 */
	private URI crearUri(String id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
}