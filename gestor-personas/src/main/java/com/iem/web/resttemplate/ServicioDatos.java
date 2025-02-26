package com.iem.web.resttemplate;

public interface ServicioDatos {
	
	String postDato(Dato dato);
	
	String getDato();
	
	String putDato(Dato dato);
	
	String deleteDato();
}