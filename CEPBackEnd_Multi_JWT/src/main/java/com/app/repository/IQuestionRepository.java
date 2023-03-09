package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Question;

public interface IQuestionRepository extends JpaRepository<Question, Long> {

}
