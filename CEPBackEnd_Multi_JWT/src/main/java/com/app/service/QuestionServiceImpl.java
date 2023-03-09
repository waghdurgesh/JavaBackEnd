package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dto.UpdateQuestionDto;
import com.app.entity.Question;
import com.app.repository.IQuestionRepository;

@Service
@Transactional
public class QuestionServiceImpl implements IQuestionService {
	@Autowired
	private IQuestionRepository queRepo;
//get all list
	@Override
	public List<Question> getAll() {
		return queRepo.findAll();
	}
//get by id
	@Override
	public Question getByQuestionId(Long questionId) {
		return queRepo.findById(questionId).get();
	}
//insert
	@Override
	public Question insertQuestion(Question transientQuestion) {
		return queRepo.save(transientQuestion);
	}
//delete
	@Override
	public String deleteQuestion(Long id) {
		if (queRepo.existsById(id)) {
			queRepo.deleteById(id);
			return "Question Succesfully Deleted";
		}
		return "Question Deletion Failed : Invalid Id";
	}
//edit
	@Override
	public Question editQuestion(Long questionId, UpdateQuestionDto detachedQuestion) {
		if (queRepo.existsById(questionId)) {
			Question upQuestion = getByQuestionId(questionId);
			upQuestion.setQuestionText(detachedQuestion.getQuestionText());
			upQuestion.setQuestionMarks(detachedQuestion.getQuestionMarks());
			return queRepo.save(upQuestion);
		}
		throw new ResourceNotFoundException("Invalid Question Id : Updation Failed");
	}

}
