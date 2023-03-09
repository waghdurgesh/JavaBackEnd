package com.app.model;

import com.app.dto.LogedInUserDto;

public class JwtResponse {

	public LogedInUserDto getDto() {
		return dto;
	}

	public void setDto(LogedInUserDto dto) {
		this.dto = dto;
	}

	private String token;
	private LogedInUserDto dto;

	public JwtResponse() {

	}

	public JwtResponse(String token, LogedInUserDto dto) {
		super();
		this.token = token;
		this.dto = dto;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
