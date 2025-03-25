package com.iem.carritos.apirest.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iem.carritos.apirest.dto.CarritoResponseDto;
import com.iem.carritos.database.entity.Carrito;
import com.iem.carritos.database.entity.EstadosCarrito;
import com.iem.carritos.database.repository.CarritoRepository;

@RestController
@RequestMapping("/carritos")
public class CarritoController {

	@Autowired
	CarritoRepository carritoRepository;

	@GetMapping
	public ResponseEntity<List<CarritoResponseDto>> getCarritos(@RequestParam("estado") EstadosCarrito estado) {

		List<Carrito> salida = carritoRepository.findByEstado(estado);

		if (salida.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(salida.stream().map(carritoEntity -> {
			CarritoResponseDto dto = new CarritoResponseDto();
			dto.setId(carritoEntity.getId());
			dto.setEstado(carritoEntity.getEstado().name());
			dto.setPrecioTotal(carritoEntity.getPrecioTotal());
			return dto;
		}).toList());
	}

	@PostMapping
	public ResponseEntity<Void> postCarrito() {

		Carrito carrito = new Carrito();
		carrito.setEstado(EstadosCarrito.ABIERTO);
		carrito.setPrecioTotal(0.0f);
		Carrito carritoGuardado = carritoRepository.save(carrito);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(carritoGuardado.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/{id-carrito}")
	public ResponseEntity<Void> deleteCarrito(@PathVariable("id-carrito") String idCarrito) {
		
		Optional<Carrito> carrito = carritoRepository.findById(idCarrito);
		if (carrito.isPresent()) {
			carrito.get().setEstado(EstadosCarrito.CERRADO);
			carritoRepository.save(carrito.get());
		}
		
		return ResponseEntity.noContent().build();
	}
}