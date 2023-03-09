package com.app.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the question_tbl database table.
 * 
 */
@Entity
@Table(name = "question_tbl")
@NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id", unique = true, nullable = false)
	private long questionId;

	@Lob
	@Column(name = "question_text", nullable = false)
	private String questionText;

	@Column(name = "question_marks", nullable = false)
	private int questionMarks;

	// bi-directional many-to-one association to Master
	@OneToMany(mappedBy = "questionTbl", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("questionTbl")
	private List<Master> masterTbls;

	// bi-directional many-to-one association to TestCase
	@OneToMany(mappedBy = "questionTbl", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("questionTbl")
//	@JsonBackReference
	private List<TestCase> testCaseTbls;

	public Question() {
	}

	public long getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public int getQuestionMarks() {
		return this.questionMarks;
	}

	public void setQuestionMarks(int questionMarks) {
		this.questionMarks = questionMarks;
	}

	public String getQuestionText() {
		return this.questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public List<Master> getMasterTbls() {
		return this.masterTbls;
	}

	public void setMasterTbls(List<Master> masterTbls) {
		this.masterTbls = masterTbls;
	}

	public Master addMasterTbl(Master masterTbl) {
		getMasterTbls().add(masterTbl);
		masterTbl.setQuestionTbl(this);

		return masterTbl;
	}

	public Master removeMasterTbl(Master masterTbl) {
		getMasterTbls().remove(masterTbl);
		masterTbl.setQuestionTbl(null);

		return masterTbl;
	}

	public List<TestCase> getTestCaseTbls() {
		return this.testCaseTbls;
	}

	public void setTestCaseTbls(List<TestCase> testCaseTbls) {
		this.testCaseTbls = testCaseTbls;
	}

	public TestCase addTestCaseTbl(TestCase testCaseTbl) {
		getTestCaseTbls().add(testCaseTbl);
		testCaseTbl.setQuestionTbl(this);

		return testCaseTbl;
	}

	public TestCase removeTestCaseTbl(TestCase testCaseTbl) {
		getTestCaseTbls().remove(testCaseTbl);
		testCaseTbl.setQuestionTbl(null);

		return testCaseTbl;
	}

}