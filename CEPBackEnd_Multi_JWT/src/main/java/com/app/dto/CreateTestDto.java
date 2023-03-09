package com.app.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTestDto {
	private long testId;
	private String testTitle;
	private String testDescription;
	private int testDuration;
	private int testMaxMarks;
	private LocalDate testEndDate;
	private LocalDate testStartDate;
	private int testMaxQuestions;
//	private List<Master> masterTbls;

}