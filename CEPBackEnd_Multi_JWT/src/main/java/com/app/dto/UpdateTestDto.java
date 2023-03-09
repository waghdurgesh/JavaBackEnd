package com.app.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateTestDto {

//	private long testId;
	private String testTitle;
	private String testDescription;
	private int testDuration;
	private int testMaxMarks;
	private LocalDate testEndDate;
	private LocalDate testStartDate;
	private int testMaxQuestions;
//	private List<Master> masterTbls;
}
