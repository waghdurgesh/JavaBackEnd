package com.app.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	// finder method for signin
	Optional<Employee> findByEmailAndPassword(String em, String pass);

	// custom query for get emp name by sal
	@Query(value = "select new com.app.pojos.Employee(firstname,lastname) from Employee e where e.salary between ?1 and ?2")
	// List<Employee> fetchEmpNamesBySalaryRange(double minSalary,double maxSalary);
	Stream<Employee> fetchEmpNamesBySalaryRange(double minSal, double maxSal);

	// custom query for find by name of emp using dto--> for specific details
	@Query(value = "select e from Employee e where e.firstname = ?1")
	Stream<Employee> fetchEmpByFirstname(String name);

	// custom query for find by name of emp using entity--> for all details
	@Query(value = "select e from Employee e where e.firstname = ?1")
	List<Employee> fetchEmpAllDetailsByFirstname(String name);

}