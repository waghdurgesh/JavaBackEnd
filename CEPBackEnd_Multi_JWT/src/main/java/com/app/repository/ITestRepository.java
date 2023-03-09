package com.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Test;

public interface ITestRepository extends JpaRepository<Test, Long> {

	List<Test> findByTestTitle(String testTitle);

	List<Test> findByTestStartDate(LocalDate testStartDate);

	List<Test> findByTestEndDate(LocalDate testEndDate);

}
