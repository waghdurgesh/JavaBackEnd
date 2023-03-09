package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.IConsoleService;

@RestController
@RequestMapping("/console")
public class ConsoleController {

	@Autowired
	IConsoleService consoleService;

//get language list	
	@GetMapping("/languages")
	public void getAlllanguages() {
		consoleService.getAlllanguages();
	}

//send code to run
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/run/{lang}/{input}")
	public String compileCode(@PathVariable String lang, @PathVariable String input, @RequestBody String objCode) {
		return consoleService.sendCompileCode(lang, input, objCode);
	}

}
