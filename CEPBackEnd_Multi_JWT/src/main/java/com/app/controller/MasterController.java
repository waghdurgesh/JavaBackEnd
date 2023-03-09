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
import com.app.dto.CreateResultDto;
import com.app.entity.Master;
import com.app.service.IMasterService;

@RestController
@RequestMapping("/master")
public class MasterController {
//dependency for service class
	@Autowired
	IMasterService masterService;

//dependency of model mapper 
//	@Autowired
//	private ModelMapper mapper;

//nosrgconst
	public MasterController() {
	}

//getall
	@GetMapping
	public List<Master> getAll() {
		return masterService.getAll();
	}

//getbyid
	@GetMapping("/findbymasterid/{adminId}/{stdId}/{testId}/{queId}")
	public ResponseEntity<?> getDetailsById(@PathVariable Long adminID, @PathVariable Long stdID,
			@PathVariable Long testID, @PathVariable Long queID) {
		try {
			return new ResponseEntity<>(masterService.getByMasterId(adminID, stdID, testID, queID), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

// getby adminid
	@GetMapping("/findbyadminid/{adminId}")
	public ResponseEntity<?> getDetailsByAdminId(@PathVariable Long adminId) {
		try {
			return new ResponseEntity<>(masterService.getByAdminId(adminId), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

// getby stdid and testid
	@GetMapping("/findbystdandtest/{stdId}/{testId}")
	public ResponseEntity<?> getByStudentIdAndTestId(@PathVariable Long stdId, @PathVariable Long testId) {
		try {
			return new ResponseEntity<>(masterService.getByStudentIdAndTestId(stdId, testId), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

// getby adminid and stdid and testid
	@GetMapping("/findbystdandtest/{adminId}/{stdId}/{testId}")
	public ResponseEntity<?> getByAdminIdAndStudentIdAndTestId(@PathVariable Long adminId, @PathVariable Long stdId,
			@PathVariable Long testId) {
		try {
			return new ResponseEntity<>(masterService.getByAdminIdAndStdIdAndTestId(adminId, stdId, testId),
					HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

//create-insert
//	@PostMapping("/create")
//	public Master addMaster(@RequestBody CreateMasterDto traMaster) {
//		Master newMaster = mapper.map(traMaster, Master.class);
//		return masterService.insertMaster(newMaster);
//	}

// create-insert by pathvariable
	@PostMapping("/create/{adminId}/{stdId}/{testId}/{queId}")
	public Master addMaster(@PathVariable Long adminId, @PathVariable Long stdId, @PathVariable Long testId,
			@PathVariable Long queId) {
		return masterService.insertMaster(adminId, stdId, testId, queId);
	}

//delete
	@DeleteMapping("/remove/{adminId}/{stdId}/{testId}/{queId}")
	public ApiResponse removeMaster(@PathVariable Long adminId, @PathVariable Long stdId, @PathVariable Long testId,
			@PathVariable Long queId) {
		return new ApiResponse(masterService.deleteMaster(adminId, stdId, testId, queId));
	}

//partial update- - will not work as updatable=false in entity
//	@PatchMapping("/update/{adminId}/{stdId}/{testId}/{queId}")
//	public Master updateMaster(@PathVariable Long adminId, @PathVariable Long stdId, @PathVariable Long testId,
//			@PathVariable Long queId, @RequestBody UpdateMasterDto detMaster) {
//		return masterService.editMaster(adminId, stdId, testId, queId, detMaster);
//	}

//create result 
	@PatchMapping("/generateresult/{adminId}/{stdId}/{testId}/{queId}")
	public Master createResult(@PathVariable Long adminId, @PathVariable Long stdId, @PathVariable Long testId,
			@PathVariable Long queId, @RequestBody CreateResultDto detResult) {
		return masterService.insertResult(adminId, stdId, testId, queId, detResult);
	}

//delete
	@PatchMapping("/removeresult/{adminId}/{stdId}/{testId}/{queId}")
	public Master removeResult(@PathVariable Long adminId, @PathVariable Long stdId, @PathVariable Long testId,
			@PathVariable Long queId) {
		return masterService.deleteResult(adminId, stdId, testId, queId);
	}

}