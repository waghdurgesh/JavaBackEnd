package com.app.entity;

public class SendCode {
	String code;
	String language;
	String input;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public SendCode(String code, String language, String input) {
		super();
		this.code = code;
		this.language = language;
		this.input = input;
	}

	public SendCode() {

	}

	public SendCode(String code) {
		super();
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;

	}

	@Override
	public String toString() {
		return "Code [code=" + code + ", language=" + language + ", input=" + input + "]";
	}

}
