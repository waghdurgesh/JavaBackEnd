package com.app.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.CreateTestDto;
import com.app.dto.UpdateTestDto;
import com.app.entity.Test;
import com.app.service.ITestService;

@RestController
@RequestMapping("/test")
public class TestController {
//dependency for service class
	@Autowired
	ITestService testService;
//dependency of model mapper 
	@Autowired
	private ModelMapper mapper;

//date binder
//	@InitBinder     
//	public void initBinder(WebDataBinder binder){
//	     binder.registerCustomEditor(       Date.class,     
//	                         new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));   
//	}
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
	}

//nosrgconst
	public TestController() {
	}

//getall
	@GetMapping
	public List<Test> getAll() {
		return testService.getAll();
	}

//get by id
	@GetMapping("/findbyid/{testId}")
	public ResponseEntity<?> getDetailsById(@PathVariable long testId) {
		try {
			return new ResponseEntity<>(testService.getByTestId(testId), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

// get by title
	@GetMapping("/findbytitle/{testTitle}")
	public ResponseEntity<?> getDetailsByTitle(@PathVariable String testTitle) {
		try {
			return new ResponseEntity<>(testService.getByTestTitle(testTitle), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

// get by startDate
	@GetMapping("/findbystartDate/{testStartDate}")
	public ResponseEntity<?> getDetailsByStartDate(
			@RequestParam(name = "testStartDate", defaultValue = "2000-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate testStartDate) {
		try {
			return new ResponseEntity<>(testService.getByTestStartDate(testStartDate), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

// get by endDate
	@GetMapping("/findbyendDate/{testEndDate}")
	public ResponseEntity<?> getDetailsByEndDate(
			@RequestParam(name = "testEndDate", defaultValue = "2000-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate testEndDate) {
		try {
			return new ResponseEntity<>(testService.getByTestEndDate(testEndDate), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

//create-insert
	@PostMapping("/create")
	public Test addTest(@RequestBody CreateTestDto traTest) {
		Test newTest = mapper.map(traTest, Test.class);
		return testService.insertTest(newTest);
	}

//delete
	@DeleteMapping("/remove/{testId}")
	public ApiResponse removeTest(@PathVariable long testId) {
		return new ApiResponse(testService.deleteTest(testId));
	}

//partial update
	@PatchMapping("/update/{testId}")
	public Test updateTest(@PathVariable long testId, @RequestBody UpdateTestDto detTest) {
		return testService.editTest(testId, detTest);
	}

}