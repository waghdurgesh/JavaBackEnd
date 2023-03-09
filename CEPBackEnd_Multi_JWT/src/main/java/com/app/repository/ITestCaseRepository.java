package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Question;
import com.app.entity.TestCase;

public interface ITestCaseRepository extends JpaRepository<TestCase, Integer> {
	
	List<TestCase> findByQuestionTbl(Question questionId);
}
