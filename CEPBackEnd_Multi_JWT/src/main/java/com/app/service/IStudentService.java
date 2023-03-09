package com.app.service;

import java.util.List;

import com.app.dto.ForgotPasswordStudentDto;
import com.app.dto.StudentWithBatchIdDto;
import com.app.dto.UpdateStudentDto;
import com.app.entity.Batch;
import com.app.entity.Login;
import com.app.entity.Student;

public interface IStudentService {

	List<Student> getAll();

	List<StudentWithBatchIdDto> getStudentIncBatchId();

	Student getByStudentId(Long studentPrn);

	List<Student> getStudentByBatchId(Batch batch);

//	Student insertStudent(Student transientStudent);

	String deleteStudent(Long id);

	Student editStudent(Long studentId, UpdateStudentDto detachedStudent);

//	Student authenticateStudent(LoginDto logDtoStudent);

	Student getByFirstnameAndLastname(String firstName, String lastName);

	Student getByRollno(String rollNo);

	Student getStudentPassword(ForgotPasswordStudentDto studentDetails);

	Login insertStudent(Student transientStudent, Long long1);

}
