package com.app.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.CreateQuestionDto;
import com.app.dto.UpdateQuestionDto;
import com.app.entity.Question;
import com.app.service.IQuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
//dependency for service class
	@Autowired
	IQuestionService queService;
//dependency of model mapper 
	@Autowired
	private ModelMapper mapper;

//nosrgconst
	public QuestionController() {
	}

//getall
	@GetMapping
	public List<Question> getAll() {
		return queService.getAll();
	}

//getbyid
	@GetMapping("/findbyid/{questionId}")
	public ResponseEntity<?> getDetailsById(@PathVariable long questionId) {
		try {
			return new ResponseEntity<>(queService.getByQuestionId(questionId), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

//create-insert
	@PostMapping("/create")
	public Question addQuestion(@RequestBody CreateQuestionDto traQuestion) {
		Question newQuestion = mapper.map(traQuestion, Question.class);
		return queService.insertQuestion(newQuestion);
	}

//delete
	@DeleteMapping("/remove/{questionId}")
	public ApiResponse removeQuestion(@PathVariable long questionId) {
		return new ApiResponse(queService.deleteQuestion(questionId));
	}

//partial update
	@PatchMapping("/update/{questionId}")
	public Question updateQuestion(@PathVariable long questionId, @RequestBody UpdateQuestionDto detQuestion) {
		return queService.editQuestion(questionId, detQuestion);
	}

}