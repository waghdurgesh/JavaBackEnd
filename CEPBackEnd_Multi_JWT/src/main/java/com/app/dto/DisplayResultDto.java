package com.app.dto;

import com.app.entity.Admin;
import com.app.entity.Student;
import com.app.entity.Test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DisplayResultDto {
	private Admin resultAdmin;
	private Student resultStudent;
	private Test resultTest;
	private int resultObtainedMarks;
	private int resultExamMarks;
}