package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SubmitAnswerDto {

	// insertAnswer(Long adminID, Long stdID, Long testID, Long queID, String
	// language, String code)

	// compileCode(@PathVariable String lang, @PathVariable String input,
	// @RequestBody String objCode)
	
//Long adminID, Long stdID, Long testID, Long queID, String language, String code
	private long adminID;
	private long stdID;
	private long testID;
	private long queID;
	private int queObtainedMarks;
	private int passCount;
//	private List<TestCase> passedCases;
	private int failCount;
//	private List<TestCase> failedCases;
	private String language;
//	private String code;

}