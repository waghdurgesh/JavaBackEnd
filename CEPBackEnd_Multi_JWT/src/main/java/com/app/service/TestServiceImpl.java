package com.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dto.UpdateTestDto;
import com.app.entity.Test;
import com.app.repository.ITestRepository;

@Service
@Transactional
public class TestServiceImpl implements ITestService {
	@Autowired
	private ITestRepository testRepo;

//get all list
	@Override
	public List<Test> getAll() {
		return testRepo.findAll();
	}

//get by id
	@Override
	public Test getByTestId(Long testId) {
		return testRepo.findById(testId).get();
	}

//insert
	@Override
	public Test insertTest(Test transientTest) {
		return testRepo.save(transientTest);
	}

//delete
	@Override
	public String deleteTest(Long id) {
		if (testRepo.existsById(id)) {
			testRepo.deleteById(id);
			return "Test Succesfully Deleted";
		}
		return "Test Deletion Failed : Invalid Id";
	}
//edit

//	private String testTitle;
//	private String testDescription;
//	private int testDuration;
//	private int testMaxMarks;
//	private Date testEndDate;
//	private Date testStartDate;

	@Override
	public Test editTest(Long testId, UpdateTestDto detachedTest) {
		if (testRepo.existsById(testId)) {
			Test upTest = getByTestId(testId);
			upTest.setTestTitle(detachedTest.getTestTitle());
			upTest.setTestDescription(detachedTest.getTestDescription());
			upTest.setTestDuration(detachedTest.getTestDuration());
			upTest.setTestMaxMarks(detachedTest.getTestMaxMarks());
			upTest.setTestEndDate(detachedTest.getTestEndDate());
			upTest.setTestStartDate(detachedTest.getTestStartDate());
			upTest.setTestMaxQuestions(detachedTest.getTestMaxQuestions());
			return testRepo.save(upTest);
		}
		throw new ResourceNotFoundException("Invalid Test Id : Updation Failed");
	}

//get by title
	@Override
	public List<Test> getByTestTitle(String testTitle) {
		return testRepo.findByTestTitle(testTitle);
	}

//get by Start Date
	@Override
	public List<Test> getByTestStartDate(LocalDate testStart) {
		return testRepo.findByTestStartDate(testStart);
	}

//get by End Date
	@Override
	public List<Test> getByTestEndDate(LocalDate testEnd) {
		return testRepo.findByTestEndDate(testEnd);
	}

}
