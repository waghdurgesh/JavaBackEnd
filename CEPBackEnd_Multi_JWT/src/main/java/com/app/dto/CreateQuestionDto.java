package com.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuestionDto {

	private long questionId;

	private int questionMarks;

	private String questionText;

}