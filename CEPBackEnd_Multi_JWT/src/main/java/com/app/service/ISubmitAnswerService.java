package com.app.service;

import com.app.dto.SubmitAnswerDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ISubmitAnswerService {

//insert answer

	// return void
	SubmitAnswerDto insertAnswer(Long adminID, Long stdID, Long testID, Long queID, String language, String code) throws JsonMappingException, JsonProcessingException;

}
