package com.iem.sucursal.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.iem.sucursal.dto.SucursalDto;
import com.iem.sucursal.dto.TipoEvento;
import com.iem.sucursal.producer.SucursalProducer;

@Service
public class SucursalService {
	
	private static final Logger log = LoggerFactory.getLogger(SucursalService.class);
	
	@Value("${custom.topic.sucursal}")
	private String topicSucursal;
	
	@Autowired
	SucursalProducer sucursalProducer;
	
	public List<String> info = new ArrayList<>();
	
	public void nuevoCoche(String datosNuevoCoche) {
		log.info("Ha llegado un nuevo coche");
		info.add(datosNuevoCoche);
	}
	
	public void ventaCoche(String datosCoche) {
		log.info("Se ha vendido un coche");
		SucursalDto sucursal = new SucursalDto();
		sucursal.setId("Nuevo id");
		sucursal.setDatos("Datos del coche");
		sucursal.setTipoeEvento(TipoEvento.VENTA);
		sucursalProducer.sendMessage(topicSucursal, sucursal);
	}

}