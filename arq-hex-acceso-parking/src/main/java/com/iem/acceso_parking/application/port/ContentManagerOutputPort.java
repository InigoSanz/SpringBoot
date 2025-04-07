package com.iem.acceso_parking.application.port;

public interface ContentManagerOutputPort {

	String guardarImagen(byte[] imagen);

	boolean comparaImagenes(String idImagenContentManager, byte[] imagenNueva);
}