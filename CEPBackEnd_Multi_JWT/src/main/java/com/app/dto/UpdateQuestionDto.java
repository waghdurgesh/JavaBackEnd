package com.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateQuestionDto {
	
//	private long questionId;
	private String questionText;
	private int questionMarks;
}
