package com.app.service;

import java.util.List;

import com.app.dto.UpdateTestCaseDto;
import com.app.entity.Question;
import com.app.entity.TestCase;

public interface ITestCaseService {
//get all
	List<TestCase> getAll();
//get by id
	TestCase getByTestCaseId(int testCaseId);
//insert
	TestCase insertTestCase(TestCase transientTestCase);
//delete
	String deleteTestCase(int id);
//edit
	TestCase editTestCase(int testCaseId, UpdateTestCaseDto detachedTestCase);
//get by question id
	List<TestCase> getTestCaseByQuestionId(Question questiontbl);

}
