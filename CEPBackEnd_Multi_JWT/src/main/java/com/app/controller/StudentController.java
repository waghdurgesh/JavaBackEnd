package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.ForgotPasswordStudentDto;
import com.app.dto.StudentWithBatchIdDto;
import com.app.dto.UpdateStudentDto;
import com.app.entity.Student;
import com.app.service.IBatchService;
import com.app.service.IStudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	IStudentService studentService;

	@Autowired
	IBatchService batchService;

//	@Autowired
//	private ModelMapper mapper;

	public StudentController() {
	}

	@GetMapping
	public List<Student> getAll() {
		return studentService.getAll();
	}

	@GetMapping("/studentincbatchid")
	public List<StudentWithBatchIdDto> getstudentwithbatch() {
		return studentService.getStudentIncBatchId();
	}

	@GetMapping("/findbyid/{studentPrn}")
	public ResponseEntity<?> getDetailsById(@PathVariable Long studentPrn) {
		try {
			return new ResponseEntity<>(studentService.getByStudentId(studentPrn), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/findbyrollno/{rollno}")
	public ResponseEntity<?> getStudentDetailsByRollno(@PathVariable String rollno) {
		try {
			return new ResponseEntity<>(studentService.getByRollno(rollno), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/findbyname/{firstname}/{lastname}")
	public ResponseEntity<?> getStudentDetailsByName(@PathVariable String firstname, @PathVariable String lastname) {
		try {
			return new ResponseEntity<>(studentService.getByFirstnameAndLastname(firstname, lastname), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/findbybatch/{batchId}")
	public ResponseEntity<?> getStudentDetailsByBatchId(@PathVariable Long batchId) {
		try {
			return new ResponseEntity<>(studentService.getStudentByBatchId(batchService.getByBatchId(batchId)),
					HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

//	@PostMapping("/signup")
//	public Student addStudent(@RequestBody SignupStudentDto traStudent) {
////		Student newStudent = mapper.map(traStudent, Student.class);
//		Batch byBatchId = batchService.getByBatchId(traStudent.getBatchId());
//		Student stdData = new Student(traStudent.getStdPrn(), traStudent.getStdRollno(), traStudent.getStdFirstname(),
//				traStudent.getStdLastname(), traStudent.getStdEmail(), traStudent.getStdPassword(), byBatchId);
////		stdData.setStudentRole("ROLE_STUDENT");
//		System.out.println(stdData);
//
//		return studentService.insertStudent(stdData);
////		Student(long stdPrn, String stdRollno, String stdFirstname, String stdLastname, String stdEmail,
////				String stdPassword, Batch batchTbl)
//	}

//	@PostMapping("/signin")
//	public Student validateStudent(@RequestBody LoginDto logDtoStudent) {
//		return studentService.authenticateStudent(logDtoStudent);
//	}

	@DeleteMapping("/remove/{studentId}")
	public ApiResponse removeStudent(@PathVariable Long studentId) {
//		System.out.println("in del emp " + studentId);
		return new ApiResponse(studentService.deleteStudent(studentId));
	}

	@PutMapping("/update/{studentId}")
	public Student updateStudent(@PathVariable Long studentId, @RequestBody UpdateStudentDto detStudent) {
		return studentService.editStudent(studentId, detStudent);
	}

	@PostMapping("/forgotpassword")
	public Student forgotStudentPassword(@RequestBody ForgotPasswordStudentDto detStudent) {
		return studentService.getStudentPassword(detStudent);
	}

}
