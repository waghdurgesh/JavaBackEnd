package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Batch;
import com.app.entity.Login;
import com.app.entity.Student;

public interface IStudentRepository extends JpaRepository<Student, Long> {
	Optional<Student> findByStdEmailAndStdPassword(String email, String password);

	Optional<Student> findByStdEmailAndStdFirstnameAndStdLastnameAndStdPrnAndStdRollno(String email, String firstName,
			String lastName, Long prn, String rollNo);

	List<Student> findByBatchTbl(Batch batch);

	Optional<Student> findByStdFirstnameAndStdLastname(String firstName, String lastName);

	Optional<Student> findByStdRollno(String rollNo);

	Optional<Student> findByLogin(Login login);
}
