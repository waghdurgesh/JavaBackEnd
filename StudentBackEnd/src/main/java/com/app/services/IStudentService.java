package com.app.services;

import java.util.List;

import com.app.entities.Student;

public interface IStudentService {
	
	//get
	List<Student> getAllStudent();
	
	//add
	Student insertStudent(Student stud);
		
	//remove
	void deleteStudent(Long id);
	
	//update
	Student editStudent(Student stud);

}
