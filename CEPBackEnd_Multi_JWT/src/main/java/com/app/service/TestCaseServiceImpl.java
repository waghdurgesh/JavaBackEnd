package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.custom_exception.ResourceNotFoundException;
import com.app.dto.UpdateTestCaseDto;
import com.app.entity.Question;
import com.app.entity.TestCase;
import com.app.repository.ITestCaseRepository;

@Service
@Transactional
public class TestCaseServiceImpl implements ITestCaseService {
	@Autowired
	private ITestCaseRepository testCaseRepo;

//get all list
	@Override
	public List<TestCase> getAll() {
		return testCaseRepo.findAll();
	}

//get by id
	@Override
	public TestCase getByTestCaseId(int testCaseId) {
		return testCaseRepo.findById(testCaseId).get();
	}

//insert
	@Override
	public TestCase insertTestCase(TestCase transientTestCase) {
		return testCaseRepo.save(transientTestCase);
	}

//delete
	@Override
	public String deleteTestCase(int id) {
		if (testCaseRepo.existsById(id)) {
			testCaseRepo.deleteById(id);
			return "TestCase Succesfully Deleted";
		}
		return "TestCase Deletion Failed : Invalid Id";
	}

//edit
	@Override
	public TestCase editTestCase(int testCaseId, UpdateTestCaseDto detachedTestCase) {
		if (testCaseRepo.existsById(testCaseId)) {
			TestCase upTestCase = getByTestCaseId(testCaseId);
			upTestCase.setCaseInput(detachedTestCase.getCaseInput());
			upTestCase.setCaseMarks(detachedTestCase.getCaseMarks());
			upTestCase.setCaseOutput(detachedTestCase.getCaseOutput());
			return testCaseRepo.save(upTestCase);
		}
		throw new ResourceNotFoundException("Invalid TestCase Id : Updation Failed");
	}

	@Override
	public List<TestCase> getTestCaseByQuestionId(Question byQuestionTbl) {
		return testCaseRepo.findByQuestionTbl(byQuestionTbl);
	}


}
