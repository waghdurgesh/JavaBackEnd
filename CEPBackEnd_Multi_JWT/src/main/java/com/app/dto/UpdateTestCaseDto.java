package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateTestCaseDto {

//	private int caseId;
	private String caseInput;
	private int caseMarks;
	private String caseOutput;
//	private long questionId;

}