package com.app.services;

import java.util.List;
import java.util.Optional;

import com.app.dto.EmployeeResponse;
import com.app.dto.EmployeeSpecificResp;
import com.app.dto.LoginRequestDto;
import com.app.pojos.Employee;

public interface IEmployeeService {
//get emp
	List<Employee> getAllEmployee();

//add emp
	Employee addNewEmployee(Employee emp);

//remove emp
	String deleteEmployee(Long id);

//update method using response entity
//update emp with resp entity
//ResponseEntity<Employee> editEmployee(Long id, Employee emp);

//update emp
	Employee updateEmployeeDetails(Employee detachedEmp);

//get salary in range
	List<EmployeeResponse> getEmpsBySalary(double minSal, double maxSal);

//get emp by Id
	Optional<Employee> getEmpById(Long id);

//get emp by firstname dto
	List<EmployeeResponse> getEmpsByFirstName(String firstname);

//get emp by firstname entity
	List<Employee> getAllDetailsByFirstName(String firstname);

// employee authentication
	EmployeeSpecificResp authenticateEmp(LoginRequestDto dto);

}
