package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExportResultDto {
//	private long adminFirstname;
//	private long adminLastname;
	private long testId;
	private String testName;
	private long studentPrn;
	private String studentRollNo;
	private String studentFirstname;
	private String studentLastname;
	private int studentObtMarks;
	private int examTotalMarks;
}