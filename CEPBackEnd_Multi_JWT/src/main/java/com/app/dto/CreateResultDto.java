package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateResultDto {
//	private long resultId;
//	private long resultAdminId;
//	private long resultStudentPrn;
//	private long resultTestId;
//	private long resultQuestionId;
	private int resultObtainedMarks;
	private String resultCodeAnswer;
}