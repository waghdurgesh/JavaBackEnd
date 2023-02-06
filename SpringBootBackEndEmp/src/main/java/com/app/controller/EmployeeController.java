package com.app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.EmployeeResponse;
import com.app.dto.LoginRequestDto;
import com.app.pojos.Employee;
import com.app.services.IEmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/employees")
public class EmployeeController {
	// Dependency Service layer I/F
	@Autowired
	private IEmployeeService empService;

	public EmployeeController() {
		System.out.println("***Inside Emp Controller*** Def. Constructor =" + getClass());
	}

	// get mapping with http://localhost:port/employees
	// get all emp --> get
	@GetMapping
	public List<Employee> getEmployees() {
		System.out.println("***Inside get Employee***");
		return empService.getAllEmployee();
	}

	// add new emp --> post
	@PostMapping
	public Employee saveEmployee(@RequestBody Employee emp) {
		System.out.println("***In save emp***" + emp);
		return empService.addNewEmployee(emp);
	}

	// delete emp --. delete
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		System.out.println("***In Delete Emp***");
		empService.deleteEmployee(id);
	}

//update method using response entity
//	@PutMapping("/{id}")
//	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee emp) {
//		System.out.println("***In Update Employee***");
//		return empService.editEmployee(id, emp);
//	}

	// update emp --> put
	@PutMapping
	public Employee updateEmpDetails(@RequestBody Employee emp) {
		System.out.println("***In Update Emp Details***" + emp.getId());
		return empService.updateEmployeeDetails(emp);
	}

	// get all emp by salary range --> get
	@GetMapping("/salary")
	public List<EmployeeResponse> getAllEmpsBySalaryRange(@RequestParam double minSal, double maxSal) {
		System.out.println("***In get Emp By Salary***" + minSal + " " + maxSal);
		return empService.getEmpsBySalary(minSal, maxSal);
	}

	// get emp by id --> get
	@GetMapping("/{id}")
	public Optional<Employee> getEmployeeById(@RequestParam Long id) {
		System.out.println("***In Get Emp Details By Id***" + " For Id : " + id);
		return empService.getEmpById(id);
	}

	// get first name and last name of emp by name --> get
	@GetMapping("/dto/{names}")
	public List<EmployeeResponse> getEmpsByFirstname(@RequestParam String fname) {
		System.out.println("***In Get Emp Details By Name***" + " For Firstname : " + fname);
		return empService.getEmpsByFirstName(fname);
	}

	// get all details by name --> get
	@GetMapping("/entity/{firstname}")
	public List<Employee> getAllEmpsByFirstname(@RequestParam String fname) {
		System.out.println("***In Get Emp Details By Name***" + " For Firstname : " + fname);
		return empService.getAllDetailsByFirstName(fname);
	}

	// authentication --> post
	@PostMapping("/signin")
	public ResponseEntity<?> validateEmployee(@RequestBody @Valid LoginRequestDto dto) {
		System.out.println("in emp signin " + dto);
		// try {
		return ResponseEntity.ok(empService.authenticateEmp(dto));
//		} catch (RuntimeException e) {
//			System.out.println("err in emp controller " + e);
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).
//					body(new ApiResponse(e.getMessage()));
//		}

	}
}
