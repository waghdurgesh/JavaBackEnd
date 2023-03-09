package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.CompilerResponseDto;
import com.app.dto.SubmitAnswerDto;
import com.app.entity.Master;
import com.app.entity.TestCase;
import com.app.repository.IMasterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class SubmitAnswerServiceImpl implements ISubmitAnswerService {
//dependency
	@Autowired
	private IMasterRepository masterRepo;

	@Autowired
	private IMasterService masterService;

	@Autowired
	private IConsoleService consoleService;

//	@Autowired
//	private ModelMapper mapper;

//Here ans means adminId+StudentId+TestId+QuestionId+language+Code

//void return
	@Override
	public SubmitAnswerDto insertAnswer(Long adminID, Long stdID, Long testID, Long queID, String language, String code)
			throws JsonMappingException, JsonProcessingException {
//get by master id	
		Master objMaster = masterService.getByMasterId(adminID, stdID, testID, queID);
//insert code to masterobject
		objMaster.setResultCodeAnswer(code);
//set initial marks to zero
		objMaster.setResultObtainedMarks(0);
//list of test cases from question
		List<TestCase> listTestCase = objMaster.getQuestionTbl().getTestCaseTbls();
//list of passed and failed test cases
		List<TestCase> passedCases = new ArrayList<TestCase>();
		List<TestCase> failedCases = new ArrayList<TestCase>();
//loop for giving code to compiler
		for (int i = 0; i < listTestCase.size(); i++) {
//single test case from list
			TestCase testCase = listTestCase.get(i);
//send to compiler and store response
			String compilerResponse = consoleService.sendCompileCode(language, testCase.getCaseInput(), code);
//object mapping for response
			ObjectMapper objectMapper = new ObjectMapper();
			CompilerResponseDto compileResult = objectMapper.readValue(compilerResponse, CompilerResponseDto.class);
//condition checking for answer correctness
			System.out.println(compileResult.getOutput());
			System.out.println(testCase.getCaseOutput());
			// check for pass and failed
			if (testCase.getCaseOutput().equals(compileResult.getOutput())) {
				passedCases.add(listTestCase.get(i));
//get old marks
				int oldMarks = objMaster.getResultObtainedMarks();
//setnew marks concat to old
				objMaster.setResultObtainedMarks(oldMarks + testCase.getCaseMarks());
				System.out.println(oldMarks + testCase.getCaseMarks());
			} else {
				failedCases.add(listTestCase.get(i));
			}
		}
		masterRepo.save(objMaster);
		SubmitAnswerDto objResp = new SubmitAnswerDto(adminID, stdID, testID, queID, objMaster.getResultObtainedMarks(),
				passedCases.size(), failedCases.size(), language);
//		SubmitAnswerDto objResp = new SubmitAnswerDto(adminID, stdID, testID, queID, objMaster.getResultObtainedMarks(),
//				passedCases.size(), passedCases, failedCases.size(), failedCases, language, code);
		return objResp;
	}
}
