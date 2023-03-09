package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.EmailService;

@RestController
public class SendEmailController {

	@Autowired
	private EmailService emailService;

	@GetMapping("/sendmail")
	public String sendEmail() {
		try {
			emailService.sendEmailsToStudents();
			return "email sent to students";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error sendig email";
	}
}
