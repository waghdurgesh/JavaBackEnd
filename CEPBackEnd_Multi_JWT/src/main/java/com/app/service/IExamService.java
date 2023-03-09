package com.app.service;

import java.util.ArrayList;
import java.util.List;

import com.app.dto.DisplayResultDto;
import com.app.dto.ExportResultDto;
import com.app.entity.Question;
import com.app.entity.Student;
import com.app.entity.Test;

public interface IExamService {
//get exam by admin
	List<Test> getTestByAdmin(Long adminID);

// get student by test, admin
	List<Student> getStudentByTest(Long adminID, Long testID);

// get question by test , admin
	List<Question> getQuestionByTest(Long adminID, Long testID);

//get question fro exam by admin, test, std
	List<Question> getQuestionFromExam(Long adminID, Long testID, Long studentID);
//insert exam
	// return list
//	List<Master> insertExam(Long adminID, ArrayList<Long> stdIdList, Long testID, ArrayList<Long> queIdList);

// return void
	void insertExam(Long adminID, ArrayList<Long> stdIdList, Long testID, ArrayList<Long> queIdList);

//get total marks from id 
	int getTotalMarks(Long studentPrn, Long testId, Long adminId);

// get obj total marks from id
	DisplayResultDto getResult(Long studentPrn, Long testId, Long adminId);

// get list of result from batch id
	List<ExportResultDto> getResultListfromTest(Long adminID, Long testID);

//get list of result from batch id
	List<ExportResultDto> getResultListfromBatch(Long adminId, Long testId, Long batchId);

}
