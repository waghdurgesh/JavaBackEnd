package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dto.CreateResultDto;
import com.app.entity.Admin;
import com.app.entity.Master;
import com.app.entity.MasterPK;
import com.app.entity.Question;
import com.app.entity.Student;
import com.app.entity.Test;
import com.app.repository.IMasterRepository;

@Service
@Transactional
public class MasterServiceImpl implements IMasterService {
//dependency
	@Autowired
	private IMasterRepository masterRepo;

	@Autowired
	private IAdminService adminService;

	@Autowired
	private IStudentService studentService;

	@Autowired
	private ITestService testService;

	@Autowired
	private IQuestionService queService;

//get all list
	@Override
	public List<Master> getAll() {
		return masterRepo.findAll();
	}

//get by id
	@Override
	public Master getByMasterId(Long adminID, Long stdID, Long testID, Long queID) {
		MasterPK objMasterPk = new MasterPK(adminID, stdID, testID, queID);
		return masterRepo.findById(objMasterPk).get();
	}

//insert
	@Override
	public Master insertMaster(Long adminID, Long stdID, Long testID, Long queID) {
		MasterPK inMasterPk = new MasterPK(adminID, stdID, testID, queID);
		Admin inAdmin = adminService.getByAdminId(adminID);
		Student inStudent = studentService.getByStudentId(stdID);
		Test inTest = testService.getByTestId(testID);
		Question inQuestion = queService.getByQuestionId(queID);
		Master transientMaster = new Master(inMasterPk, inAdmin, inStudent, inTest, inQuestion);
		return masterRepo.save(transientMaster);
	}

//delete
	@Override
	public String deleteMaster(Long adminID, Long stdID, Long testID, Long queID) {
		MasterPK delMasterPk = new MasterPK(adminID, stdID, testID, queID);
		if (masterRepo.existsById(delMasterPk)) {
			masterRepo.deleteById(delMasterPk);
			return "Master Succesfully Deleted";
		}
		return "Master Deletion Failed : Invalid Id";
	}

//edit- will not work as updatable=false in entity
//	@Override
//	public Master editMaster(Long adminID, Long stdID, Long testID, Long queID, UpdateMasterDto detachedMaster) {
//		MasterPK objMasterPk = new MasterPK(adminID, stdID, testID, queID);
//		if (masterRepo.existsById(objMasterPk)) {
//			Master upMaster = getByMasterId(adminID, stdID, testID, queID);
//			upMaster.setResultCodeAnswer(detachedMaster.getResultCodeAnswer());
//			upMaster.setResultObtainedMarks(detachedMaster.getResultObtainedMarks());
//			return masterRepo.save(upMaster);
//		}
//		throw new ResourceNotFoundException("Invalid Master Id : Updation Failed");
//	}

//find by student and test
	@Override
	public List<Master> getByStudentIdAndTestId(Long stdId, Long testId) {
		Student objStudent = studentService.getByStudentId(stdId);
		Test objTest = testService.getByTestId(testId);
		return masterRepo.findByStudentTblAndTestTbl(objStudent, objTest);
	}

// find by admin and student and test
	@Override
	public List<Master> getByAdminIdAndStdIdAndTestId(Long adminId, Long stdId, Long testId) {
		Student objStudent = studentService.getByStudentId(stdId);
		Test objTest = testService.getByTestId(testId);
		Admin objAdmin = adminService.getByAdminId(adminId);
		return masterRepo.findByAdminTblAndStudentTblAndTestTbl(objAdmin, objStudent, objTest);
	}

//find by admin id
	@Override
	public List<Master> getByAdminId(Long adminId) {
		Admin objAdmin = adminService.getByAdminId(adminId);
		return masterRepo.findByAdminTbl(objAdmin);
	}

// insert Result
	@Override
	public Master insertResult(Long adminID, Long stdID, Long testID, Long queID, CreateResultDto detachedResult) {
		MasterPK objMasterPk = new MasterPK(adminID, stdID, testID, queID);
		if (masterRepo.existsById(objMasterPk)) {
			Master upMaster = getByMasterId(adminID, stdID, testID, queID);
			upMaster.setResultCodeAnswer(detachedResult.getResultCodeAnswer());
			upMaster.setResultObtainedMarks(detachedResult.getResultObtainedMarks());
			return masterRepo.save(upMaster);
		}
		throw new ResourceNotFoundException("Invalid Master Id : Create result Failed");
	}

// delete Result
	@Override
	public Master deleteResult(Long adminID, Long stdID, Long testID, Long queID) {
		MasterPK objMasterPk = new MasterPK(adminID, stdID, testID, queID);
		if (masterRepo.existsById(objMasterPk)) {
			Master upMaster = getByMasterId(adminID, stdID, testID, queID);
			upMaster.setResultCodeAnswer(null);
			upMaster.setResultObtainedMarks(0);
			return masterRepo.save(upMaster);
		}
		throw new ResourceNotFoundException("Invalid Master Id : Delete result Failed");
	}
}
