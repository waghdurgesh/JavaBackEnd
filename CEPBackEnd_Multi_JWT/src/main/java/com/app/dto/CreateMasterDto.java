package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateMasterDto {

//	private long masterId;
	private long adminId;
	private long studentId;
	private long testId;
	private long questionId;
//	private Result resultId;

}