package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignupStudentDto {
	private long stdPrn;
	private String stdRollno;
	private String stdFirstname;
	private String stdLastname;
	private String stdEmail;
	private String stdPassword;
	private long batchId;
}

