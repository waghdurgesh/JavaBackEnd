package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Student;
import com.app.services.IStudentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/students")
public class StudentContoller {
	@Autowired
	private IStudentService studentService;

	@GetMapping
	public List<Student> getStudents() {
		System.out.println("***In contoller get ALL--> Get***");
		return studentService.getAllStudent();
	}

	@PostMapping
	public Student addStudent(@RequestBody Student stud) {
		System.out.println("***In contoller Add Student--> post***");
		return studentService.insertStudent(stud);
	}

	@DeleteMapping
	public void removeStudent(@RequestParam Long id) {
		System.out.println("***In contoller Delete Student--> Delete***");
		studentService.deleteStudent(id);
	}

	@PutMapping
	public Student updateStudent(@RequestBody Student stud) {
		System.out.println("***In contoller Update Student--> Put***");
		return studentService.editStudent(stud);
	}

}
