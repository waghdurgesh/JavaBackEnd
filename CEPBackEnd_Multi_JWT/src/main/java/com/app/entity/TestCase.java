package com.app.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the test_case_tbl database table.
 * 
 */
@Entity
@Table(name = "test_case_tbl")
@NamedQuery(name = "TestCase.findAll", query = "SELECT t FROM TestCase t")
public class TestCase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "case_id", unique = true, nullable = false)
	private int caseId;

	@Lob
	@Column(name = "case_input", nullable = false)
	private String caseInput;

	@Column(name = "case_marks", nullable = false)
	private int caseMarks;

	@Lob
	@Column(name = "case_output", nullable = false)
	private String caseOutput;

	// bi-directional many-to-one association to Question
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "question_id", nullable = false)
	@JsonIgnoreProperties("testCaseTbls")
//	@JsonBackReference
	private Question questionTbl;

	public TestCase() {
	}

	public int getCaseId() {
		return this.caseId;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	public String getCaseInput() {
		return this.caseInput;
	}

	public void setCaseInput(String caseInput) {
		this.caseInput = caseInput;
	}

	public int getCaseMarks() {
		return this.caseMarks;
	}

	public void setCaseMarks(int caseMarks) {
		this.caseMarks = caseMarks;
	}

	public String getCaseOutput() {
		return this.caseOutput;
	}

	public void setCaseOutput(String caseOutput) {
		this.caseOutput = caseOutput;
	}

	public Question getQuestionTbl() {
		return this.questionTbl;
	}

	public void setQuestionTbl(Question questionTbl) {
		this.questionTbl = questionTbl;
	}
//const

	public TestCase(int caseId, String caseInput, int caseMarks, String caseOutput, Question questionTbl) {
		super();
		this.caseId = caseId;
		this.caseInput = caseInput;
		this.caseMarks = caseMarks;
		this.caseOutput = caseOutput;
		this.questionTbl = questionTbl;
	}

}