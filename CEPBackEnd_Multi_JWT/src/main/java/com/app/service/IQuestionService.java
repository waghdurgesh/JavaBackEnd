package com.app.service;

import java.util.List;

import com.app.dto.UpdateQuestionDto;
import com.app.entity.Question;

public interface IQuestionService {
//get all
	List<Question> getAll();
//get by id
	Question getByQuestionId(Long questionId);
//insert
	Question insertQuestion(Question transientQuestion);
//delete
	String deleteQuestion(Long id);
//edit
	Question editQuestion(Long questionId, UpdateQuestionDto detachedQuestion);

}
