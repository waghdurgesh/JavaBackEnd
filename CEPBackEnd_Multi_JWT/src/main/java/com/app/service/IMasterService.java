package com.app.service;

import java.util.List;

import com.app.dto.CreateResultDto;
import com.app.entity.Master;

public interface IMasterService {
//get all
	List<Master> getAll();

//get by id
	Master getByMasterId(Long adminID, Long stdID, Long testID, Long queID);

//get by stdid and testid
	List<Master> getByStudentIdAndTestId(Long stdId, Long testId);

// get by stdid and testid and queId
	List<Master> getByAdminIdAndStdIdAndTestId(Long adminId, Long stdId, Long testId);

//insert
	Master insertMaster(Long adminID, Long stdID, Long testID, Long queID);

//insert result
	Master insertResult(Long adminID, Long stdID, Long testID, Long queID, CreateResultDto inResult);

//delete
	String deleteMaster(Long adminID, Long stdID, Long testID, Long queID);

//delete result
	Master deleteResult(Long adminID, Long stdID, Long testID, Long queID);

//edit- will not work as updatable=false in entity
//	Master editMaster(Long adminID, Long stdID, Long testID, Long queID, UpdateMasterDto detachedMaster);

//
	List<Master> getByAdminId(Long adminId);

}
