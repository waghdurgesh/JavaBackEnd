package com.app.service;

import java.time.LocalDate;
import java.util.List;

import com.app.dto.UpdateTestDto;
import com.app.entity.Test;

public interface ITestService {
//get all
	List<Test> getAll();

//get by id
	Test getByTestId(Long testId);

//insert
	Test insertTest(Test transientTest);

//delete
	String deleteTest(Long id);

//edit
	Test editTest(Long testId, UpdateTestDto detachedTest);

//get by title
	List<Test> getByTestTitle(String testTitle);

// get by title
	List<Test> getByTestStartDate(LocalDate testStart);

// get by title
	List<Test> getByTestEndDate(LocalDate testEnd);
}
