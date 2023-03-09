package com.app.service;

public interface IConsoleService {
	// get language list
	void getAlllanguages();

	// send code to console
	String sendCompileCode(String language, String input, String code);

}
