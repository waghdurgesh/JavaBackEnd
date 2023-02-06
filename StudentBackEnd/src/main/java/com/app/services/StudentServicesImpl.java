package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.entities.Student;
import com.app.repository.StudentRepository;

@Service
public class StudentServicesImpl implements IStudentService {
	@Autowired
	private StudentRepository studentRepo;

	@Override
	public List<Student> getAllStudent() {
		return studentRepo.findAll();
	}

	@Override
	public Student insertStudent(Student stud) {
		return studentRepo.save(stud);
	}

	@Override
	public void deleteStudent(Long id) {
		studentRepo.deleteById(id);
	}

	@Override
	public Student editStudent(Student stud) {
		if (studentRepo.existsById(stud.getId())) {
			return studentRepo.save(stud);
		}
		throw new ResourceNotFoundException("invalid ID");
	}
}
