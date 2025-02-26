package com.iem.web.resttemplate;

import java.io.Serializable;

public class Dato implements Serializable {

	private static final long serialVersionUID = -2567466011426315728L;
	
	private String dato1;
	private String dato2;

	public String getDato1() {
		return dato1;
	}

	public void setDato1(String dato1) {
		this.dato1 = dato1;
	}

	public String getDato2() {
		return dato2;
	}

	public void setDato2(String dato2) {
		this.dato2 = dato2;
	}
}