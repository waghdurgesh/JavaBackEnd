package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.CreateTestCaseDto;
import com.app.dto.UpdateTestCaseDto;
import com.app.entity.Question;
import com.app.entity.TestCase;
import com.app.service.IQuestionService;
import com.app.service.ITestCaseService;

@RestController
@RequestMapping("/testcase")
public class TestCaseController {
//dependency for service class
	@Autowired
	ITestCaseService testcaseService;

	@Autowired
	IQuestionService qService;

//dependency of model mapper 
//	@Autowired
//	private ModelMapper mapper;

//nosrgconst
	public TestCaseController() {
	}

//getall
	@GetMapping
	public List<TestCase> getAll() {
		return testcaseService.getAll();
	}

//getbyid
	@GetMapping("/findbyid/{testcaseId}")
	public ResponseEntity<?> getDetailsById(@PathVariable int testcaseId) {
		try {
			return new ResponseEntity<>(testcaseService.getByTestCaseId(testcaseId), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

//create-insert

//	@PostMapping("/create")
//	public TestCase addTestCase(@RequestBody CreateTestCaseDto traTestCase) {
//		TestCase newTestCase = mapper.map(traTestCase, TestCase.class);
//		return testcaseService.insertTestCase(newTestCase);
//	}

	@PostMapping("/create")
	public TestCase addTestCase(@RequestBody CreateTestCaseDto traTestCase) {
//		TestCase newTestCase = mapper.map(traTestCase, TestCase.class);
		Question byQuestionId = qService.getByQuestionId(traTestCase.getQuestionId());
		TestCase stdData = new TestCase(traTestCase.getCaseId(), traTestCase.getCaseInput(), traTestCase.getCaseMarks(),
				traTestCase.getCaseOutput(), byQuestionId);
		System.out.println(stdData);

		return testcaseService.insertTestCase(stdData);
//		TestCase(int caseId, String caseInput, int caseMarks, String caseOutput, Question questionTbl)
	}

//delete
	@DeleteMapping("/remove/{testcaseId}")
	public ApiResponse removeTestCase(@PathVariable int testcaseId) {
		return new ApiResponse(testcaseService.deleteTestCase(testcaseId));
	}

//partial update
	@PatchMapping("/update/{testcaseId}")
	public TestCase updateTestCase(@PathVariable int testcaseId, @RequestBody UpdateTestCaseDto detTestCase) {
		return testcaseService.editTestCase(testcaseId, detTestCase);
	}

//get by question id
	@GetMapping("/findbyquestion/{questionId}")
	public ResponseEntity<?> getTestCaseDetailsByQuestionId(@PathVariable long questionId) {
		try {
			return new ResponseEntity<>(testcaseService.getTestCaseByQuestionId(qService.getByQuestionId(questionId)),
					HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}