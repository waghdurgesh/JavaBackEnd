package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class CreateStudentDto {

	private long stdPrn;
	private String stdRollno;
	private String stdEmail;
	private String stdFirstname;
	private String stdLastname;
	private String stdPassword;
	private Long batchId;

}
