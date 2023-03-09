package com.app.dto;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateExamDto {

	private long adminId;
	private ArrayList<Long> stdId;
	private long testId;
	private ArrayList<Long> queId;

}