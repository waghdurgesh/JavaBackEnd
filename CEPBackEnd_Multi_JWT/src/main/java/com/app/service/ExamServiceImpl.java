package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.DisplayResultDto;
import com.app.dto.ExportResultDto;
import com.app.entity.Admin;
import com.app.entity.Master;
import com.app.entity.MasterPK;
import com.app.entity.Question;
import com.app.entity.Student;
import com.app.entity.Test;
import com.app.repository.IMasterRepository;

@Service
@Transactional
public class ExamServiceImpl implements IExamService {
//dependency
	@Autowired
	private IMasterRepository masterRepo;

	@Autowired
	private IAdminService adminService;

	@Autowired
	private IStudentService stdService;

	@Autowired
	private ITestService testService;

	@Autowired
	private IQuestionService queService;

	@Autowired
	private IBatchService batchService;

//Here exam means adminId+StudentsList+TestId+Questions
//get exam mapping list of test by id
	@Override
	public List<Test> getTestByAdmin(Long adminID) {
//init list
		List<Test> testList = new ArrayList<Test>();
//get list from repo for admin id
		List<Long> repoList = masterRepo.findByAdminIdGroupByTestId(adminID);
//feed object in list by loop
		for (int i = 0; i < repoList.size(); i++) {
			testList.add(i, testService.getByTestId(repoList.get(i)));
		}
		return testList;
	}

// get exam mapping list of student by testid
//	@Override
	public List<Student> getStudentByTest(Long adminID, Long testID) {

		List<Student> studentList = new ArrayList<Student>();

		List<Long> repoList = masterRepo.findByTestIdGroupByStudentPrn(adminID, testID);

		for (int i = 0; i < repoList.size(); i++) {
			studentList.add(stdService.getByStudentId(repoList.get(i)));
		}
		return studentList;
	}

//get list of question by test id
	@Override
	public List<Question> getQuestionByTest(Long adminID, Long testID) {

		List<Question> questionList = new ArrayList<Question>();

		List<Long> repoList = masterRepo.findByTestIdGroupByQuestionId(adminID, testID);

		for (int i = 0; i < repoList.size(); i++) {
			questionList.add(queService.getByQuestionId(repoList.get(i)));
		}
		return questionList;
	}

//get list of questions for particular exam
	@Override
	public List<Question> getQuestionFromExam(Long adminID, Long testID, Long studentID) {

		List<Question> questionList = new ArrayList<Question>();

		List<Long> repoList = masterRepo.findQuestionsForExam(adminID, testID, studentID);

		for (int i = 0; i < repoList.size(); i++) {
			questionList.add(queService.getByQuestionId(repoList.get(i)));
		}
		return questionList;
	}

//insert exam mapping	
//void return
	@Override
	public void insertExam(Long adminID, ArrayList<Long> stdIdList, Long testID, ArrayList<Long> queIdList) {
		// created admin obj by id
		Admin objAdmin = adminService.getByAdminId(adminID);

		// created batch obj by id
//		Batch objBatch = batchService.getByBatchId(batchID);
		// created list of student by batch obj
//		List<Student> listStudent = studentService.getStudentByBatchId(objBatch);

		// created test obj by id
		Test objTest = testService.getByTestId(testID);

		// declare list of students
		List<Student> objListStudent = new ArrayList<>();

		// insert in student list by getbystudentid from list of stdid in loop
		for (int i = 0; i < stdIdList.size(); i++) {
			// add all question objects in list
			objListStudent.add(stdService.getByStudentId(stdIdList.get(i)));
		}

		// declare list of questions
		List<Question> objListQuestion = new ArrayList<>();
		// insert in question list by getbyquestionid from list of queid in loop
		for (int i = 0; i < queIdList.size(); i++) {
			// add all question objects in list
			objListQuestion.add(queService.getByQuestionId(queIdList.get(i)));
		}

		// list of MasterPK using ID
		List<MasterPK> objListMasterPK = new ArrayList<>();
		// create masterpk objects loop for inserting id in masterpk object
		for (int i = 0; i < stdIdList.size(); i++) {

			for (int j = 0; j < queIdList.size(); j++) {
				// MasterPK(long adminId, long studentPrn, long testId, long questionId)
				objListMasterPK.add(new MasterPK(adminID, stdIdList.get(i), testID, queIdList.get(j)));
			}
		}
		// master object intialization
		Master traMaster;
		// loop for masterpk
		for (int i = 0; i < objListMasterPK.size(); i++) {
			// looop for student
			for (int j = 0; j < objListStudent.size(); j++) {
				// loop for question
				for (int k = 0; k < objListQuestion.size(); k++) {
					traMaster = new Master(objListMasterPK.get(i), objAdmin, objListStudent.get(j), objTest,
							objListQuestion.get(k));
					// for return statement
					masterRepo.save(traMaster);
				}
			}
		}
	}

	/*
	 * @Override public List<Master> insertExam(Long adminID, ArrayList<Long>
	 * stdIdList, Long testID, ArrayList<Long> queIdList) { // created admin obj by
	 * id Admin objAdmin = adminService.getByAdminId(adminID);
	 * 
	 * // created batch obj by id // Batch objBatch =
	 * batchService.getByBatchId(batchID); // created list of student by batch obj
	 * // List<Student> listStudent = studentService.getStudentByBatchId(objBatch);
	 * 
	 * // created test obj by id Test objTest = testService.getByTestId(testID);
	 * 
	 * // declare list of students List<Student> objListStudent = new ArrayList<>();
	 * ;
	 * 
	 * // insert in student list by getbystudentid from list of stdid in loop for
	 * (int i = 0; i < stdIdList.size(); i++) { // add all question objects in list
	 * objListStudent.add(stdService.getByStudentId(stdIdList.get(i))); }
	 * 
	 * // declare list of questions List<Question> objListQuestion = new
	 * ArrayList<>(); ; // insert in question list by getbyquestionid from list of
	 * queid in loop for (int i = 0; i < queIdList.size(); i++) { // add all
	 * question objects in list
	 * objListQuestion.add(queService.getByQuestionId(queIdList.get(i))); }
	 * 
	 * // list of MasterPK using ID List<MasterPK> objListMasterPK = new
	 * ArrayList<>(); // create masterpk objects loop for inserting id in masterpk
	 * object for (int i = 0; i < stdIdList.size(); i++) {
	 * 
	 * for (int j = 0; j < queIdList.size(); j++) { // MasterPK(long adminId, long
	 * studentPrn, long testId, long questionId) objListMasterPK.add(new
	 * MasterPK(adminID, stdIdList.get(i), testID, queIdList.get(j))); } } //master
	 * object intialization Master traMaster; // list for return statement
	 * List<Master> retmas = new ArrayList<Master>(); //loop for masterpk for (int i
	 * = 0; i < objListMasterPK.size(); i++) { //looop for student for (int j = 0; j
	 * < objListStudent.size(); j++) { //loop for question for (int k = 0; k <
	 * objListQuestion.size(); k++) { traMaster = new Master(objListMasterPK.get(i),
	 * objAdmin, objListStudent.get(j), objTest, objListQuestion.get(k)); //for
	 * return statement retmas.add(masterRepo.save(traMaster)); } } } return retmas;
	 * }
	 */

//get marks total
	@Override
	public int getTotalMarks(Long studentPrn, Long testId, Long adminId) {
		return masterRepo.getTotalMarks(studentPrn, testId, adminId);
	}

// get marks total
	@Override
	public DisplayResultDto getResult(Long studentPrn, Long testId, Long adminId) {
		int totalMarks = masterRepo.getTotalMarks(studentPrn, testId, adminId);
		Student objStudent = stdService.getByStudentId(studentPrn);
		Admin objAdmin = adminService.getByAdminId(adminId);
		Test objTest = testService.getByTestId(testId);
		int maxMark = objTest.getTestMaxMarks();
		DisplayResultDto objResult = new DisplayResultDto(objAdmin, objStudent, objTest, totalMarks, maxMark);
		return objResult;
	}

// get result list for test
	@Override
	public List<ExportResultDto> getResultListfromTest(Long adminId, Long testId) {
		// get student list by ids from examservimpl
		List<Student> stdList = getStudentByTest(adminId, testId);
		// init list and obj
		List<ExportResultDto> resultList = new ArrayList<ExportResultDto>();
		for (int i = 0; i < stdList.size(); i++) {
			ExportResultDto expResultObj = new ExportResultDto();
			expResultObj.setStudentPrn(stdList.get(i).getStdPrn());
			expResultObj.setStudentRollNo(stdList.get(i).getStdRollno());
			expResultObj.setStudentFirstname(stdList.get(i).getStdFirstname());
			expResultObj.setStudentLastname(stdList.get(i).getStdLastname());
			expResultObj.setTestId(testId);
			expResultObj.setTestName(testService.getByTestId(testId).getTestTitle());
			// get marks obt by examservice method
			expResultObj.setStudentObtMarks(getTotalMarks(stdList.get(i).getStdPrn(), testId, adminId));
			expResultObj.setExamTotalMarks(testService.getByTestId(testId).getTestMaxMarks());
			// add obj to list
			resultList.add(expResultObj);
		}
		return resultList;
	}

//get result list for batch
	@Override
	public List<ExportResultDto> getResultListfromBatch(Long adminId, Long testId, Long batchId) {
//get student list by id
		List<Student> stdList = stdService.getStudentByBatchId(batchService.getByBatchId(batchId));
//init list and obj
		List<ExportResultDto> resultList = new ArrayList<ExportResultDto>();
		for (int i = 0; i < stdList.size(); i++) {
			ExportResultDto expResultObj = new ExportResultDto();
			expResultObj.setStudentPrn(stdList.get(i).getStdPrn());
			expResultObj.setStudentRollNo(stdList.get(i).getStdRollno());
			expResultObj.setStudentFirstname(stdList.get(i).getStdFirstname());
			expResultObj.setStudentLastname(stdList.get(i).getStdLastname());
			expResultObj.setTestId(testId);
			expResultObj.setTestName(testService.getByTestId(testId).getTestTitle());
//get marks obt by examservice method
			expResultObj.setStudentObtMarks(getTotalMarks(stdList.get(i).getStdPrn(), testId, adminId));
			expResultObj.setExamTotalMarks(testService.getByTestId(testId).getTestMaxMarks());
//add obj to list
			resultList.add(expResultObj);
		}
		return resultList;
	}

}
