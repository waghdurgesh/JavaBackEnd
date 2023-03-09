package com.app.dto;

import java.time.LocalDateTime;

public class ErrorResponse {

	private String message;

	private LocalDateTime timeStamp = LocalDateTime.now();

	public ErrorResponse() {

	}

	public ErrorResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

}
