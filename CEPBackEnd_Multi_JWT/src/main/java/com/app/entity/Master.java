package com.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the master_tbl database table.
 * 
 */
@Entity
@Table(name = "master_tbl")
//@AllArgsConstructor
@NamedQuery(name = "Master.findAll", query = "SELECT m FROM Master m")
public class Master implements Serializable {
	private static final long serialVersionUID = 1L;

//	@Column(name = "result_id", nullable = false,unique = true, insertable = false, updatable = false)
//	private long resultId;

	@EmbeddedId
	private MasterPK masterId;

	// bi-directional many-to-one association to Admin
	@ManyToOne
	@JoinColumn(name = "admin_id", nullable = false, insertable = false, updatable = false)
	@JsonIgnoreProperties("masterTbls")
//	@JsonManagedReference
	private Admin adminTbl;

	// bi-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(name = "student_prn", nullable = false, insertable = false, updatable = false)
	@JsonIgnoreProperties("masterTbls")
//	@JsonManagedReference
	private Student studentTbl;

	// bi-directional many-to-one association to Test
	@ManyToOne
	@JoinColumn(name = "test_id", nullable = false, insertable = false, updatable = false)
	@JsonIgnoreProperties("masterTbls")
//	@JsonManagedReference
	private Test testTbl;

	// bi-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name = "question_id", nullable = false, insertable = false, updatable = false)
	@JsonIgnoreProperties("masterTbls")
//	@JsonManagedReference
	private Question questionTbl;

	@Lob
	@Column(name = "result_code_answer", insertable = true, updatable = true)
	private String resultCodeAnswer;

	@Column(name = "result_obtained_marks", insertable = true, updatable = true)
	private int resultObtainedMarks;

//	// bi-directional one-to-one association to Result
//	// @JsonBackReference
//	@JsonIgnoreProperties("masterTbl")
//	@OneToOne(mappedBy = "masterTbl", cascade = CascadeType.ALL, orphanRemoval = true)
//	private Result resultTbl;

	public Master() {
	}

	public MasterPK getMasterId() {
		return masterId;
	}

	public void setMasterId(MasterPK masterId) {
		this.masterId = masterId;
	}

//	public long getResultId() {
//		return resultId;
//	}
//
//	public void setResultId(long resultId) {
//		this.resultId = resultId;
//	}

	public Admin getAdminTbl() {
		return adminTbl;
	}

	public void setAdminTbl(Admin adminTbl) {
		this.adminTbl = adminTbl;
	}

	public Student getStudentTbl() {
		return studentTbl;
	}

	public void setStudentTbl(Student studentTbl) {
		this.studentTbl = studentTbl;
	}

	public Test getTestTbl() {
		return testTbl;
	}

	public void setTestTbl(Test testTbl) {
		this.testTbl = testTbl;
	}

	public Question getQuestionTbl() {
		return questionTbl;
	}

	public void setQuestionTbl(Question questionTbl) {
		this.questionTbl = questionTbl;
	}

	public String getResultCodeAnswer() {
		return resultCodeAnswer;
	}

	public void setResultCodeAnswer(String resultCodeAnswer) {
		this.resultCodeAnswer = resultCodeAnswer;
	}

	public int getResultObtainedMarks() {
		return resultObtainedMarks;
	}

	public void setResultObtainedMarks(int resultObtainedMarks) {
		this.resultObtainedMarks = resultObtainedMarks;
	}

	public Master(MasterPK masterId, Admin adminTbl, Student studentTbl, Test testTbl, Question questionTbl,
			String resultCodeAnswer, int resultObtainedMarks) {
		super();
		this.masterId = masterId;
		this.adminTbl = adminTbl;
		this.studentTbl = studentTbl;
		this.testTbl = testTbl;
		this.questionTbl = questionTbl;
		this.resultCodeAnswer = resultCodeAnswer;
		this.resultObtainedMarks = resultObtainedMarks;
	}

	public Master(MasterPK masterId, Admin adminTbl, Student studentTbl, Test testTbl, Question questionTbl) {
		super();
		this.masterId = masterId;
		this.adminTbl = adminTbl;
		this.studentTbl = studentTbl;
		this.testTbl = testTbl;
		this.questionTbl = questionTbl;
	}
	
	
	

}