package com.iem.kafka.dto;

public class DetailDto {

	private String type;
	private String value;

	public DetailDto() {
		// Por defecto
	}

	public DetailDto(String type, String value) {
		super();
		this.type = type;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}