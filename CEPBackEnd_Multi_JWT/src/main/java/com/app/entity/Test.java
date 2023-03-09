package com.app.entity;

import java.io.Serializable;
import java.time.LocalDate;
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

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * The persistent class for the test_tbl database table.
 * 
 */
@Entity
@Table(name = "test_tbl")
@NamedQuery(name = "Test.findAll", query = "SELECT t FROM Test t")
public class Test implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "test_id", unique = true, nullable = false)
	private long testId;

	@Column(name = "test_title", nullable = false, length = 255)
	private String testTitle;

	@Lob
	@Column(name = "test_description")
	private String testDescription;

	@Column(name = "test_duration", nullable = false)
	private int testDuration;

	@Column(name = "test_max_marks", nullable = false)
	private int testMaxMarks;
	
	@Column(name = "test_max_questions", nullable = false)
	private int testMaxQuestions;

//	@Temporal(TemporalType.DATE)
	@Column(name = "test_end_date", nullable = false)
//	@DateTimeFormat(pattern = "yyyy/MM/dd")
//	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
//	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private LocalDate testEndDate;

//	@Temporal(TemporalType.DATE)
	@Column(name = "test_start_date", nullable = false)
//	@DateTimeFormat(pattern = "yyyy/MM/dd")
//	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
//	@JsonFormat(pattern = "yyyy/MM/dd", shape = Shape.STRING)
	private LocalDate testStartDate;

	// bi-directional many-to-one association to Master
	@OneToMany(mappedBy = "testTbl", cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonIgnoreProperties("testTbl")
	@JsonBackReference
	private List<Master> masterTbls;

	public Test() {
	}

	public int getTestMaxQuestions() {
		return testMaxQuestions;
	}

	public void setTestMaxQuestions(int testMaxQuestions) {
		this.testMaxQuestions = testMaxQuestions;
	}

	public long getTestId() {
		return this.testId;
	}

	public void setTestId(long testId) {
		this.testId = testId;
	}

	public String getTestDescription() {
		return this.testDescription;
	}

	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}

	public int getTestDuration() {
		return this.testDuration;
	}

	public void setTestDuration(int testDuration) {
		this.testDuration = testDuration;
	}

	public LocalDate getTestEndDate() {
		return this.testEndDate;
	}

	public void setTestEndDate(LocalDate testEndDate) {
		this.testEndDate = testEndDate;
	}

	public int getTestMaxMarks() {
		return this.testMaxMarks;
	}

	public void setTestMaxMarks(int testMaxMarks) {
		this.testMaxMarks = testMaxMarks;
	}

	public LocalDate getTestStartDate() {
		return this.testStartDate;
	}

	public void setTestStartDate(LocalDate testStartDate) {
		this.testStartDate = testStartDate;
	}

	public String getTestTitle() {
		return this.testTitle;
	}

	public void setTestTitle(String testTitle) {
		this.testTitle = testTitle;
	}

	public List<Master> getMasterTbls() {
		return this.masterTbls;
	}

	public void setMasterTbls(List<Master> masterTbls) {
		this.masterTbls = masterTbls;
	}

	public Master addMasterTbl(Master masterTbl) {
		getMasterTbls().add(masterTbl);
		masterTbl.setTestTbl(this);

		return masterTbl;
	}

	public Master removeMasterTbl(Master masterTbl) {
		getMasterTbls().remove(masterTbl);
		masterTbl.setTestTbl(null);

		return masterTbl;
	}

//const

	public Test(long testId, String testTitle, String testDescription, int testDuration, int testMaxMarks,
			int testMaxQuestions, LocalDate testEndDate, LocalDate testStartDate) {
		super();
		this.testId = testId;
		this.testTitle = testTitle;
		this.testDescription = testDescription;
		this.testDuration = testDuration;
		this.testMaxMarks = testMaxMarks;
		this.testMaxQuestions = testMaxQuestions;
		this.testEndDate = testEndDate;
		this.testStartDate = testStartDate;
	}
}