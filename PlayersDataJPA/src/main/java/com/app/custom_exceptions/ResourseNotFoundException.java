package com.app.custom_exceptions;

@SuppressWarnings("serial")
public class ResourseNotFoundException extends RuntimeException {

	public ResourseNotFoundException(String mesg) {
		super(mesg);
	}
}
