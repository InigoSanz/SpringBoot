package com.iem.kafka.dto;

import java.util.List;

public class MessageDto {

	private String msg;
	private String user;
	private List<DetailDto> details;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<DetailDto> getDetails() {
		return details;
	}

	public void setDetails(List<DetailDto> details) {
		this.details = details;
	}
}