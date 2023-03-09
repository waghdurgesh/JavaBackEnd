package com.app.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the master_tbl database table.
 * 
 */
@Embeddable
public class MasterPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "admin_id", insertable = false, updatable = false, nullable = false)
	private long adminId;

	@Column(name = "student_prn", insertable = false, updatable = false, nullable = false)
	private long studentPrn;

	@Column(name = "test_id", insertable = false, updatable = false, nullable = false)
	private long testId;

	@Column(name = "question_id", insertable = false, updatable = false, nullable = false)
	private long questionId;

	public MasterPK() {
	}

	public long getAdminId() {
		return this.adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public long getStudentPrn() {
		return this.studentPrn;
	}

	public void setStudentPrn(long studentPrn) {
		this.studentPrn = studentPrn;
	}

	public long getTestId() {
		return this.testId;
	}

	public void setTestId(long testId) {
		this.testId = testId;
	}

	public long getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MasterPK)) {
			return false;
		}
		MasterPK castOther = (MasterPK) other;
		return (this.adminId == castOther.adminId) && (this.studentPrn == castOther.studentPrn)
				&& (this.testId == castOther.testId) && (this.questionId == castOther.questionId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.adminId ^ (this.adminId >>> 32)));
		hash = hash * prime + ((int) (this.studentPrn ^ (this.studentPrn >>> 32)));
		hash = hash * prime + ((int) (this.testId ^ (this.testId >>> 32)));
		hash = hash * prime + ((int) (this.questionId ^ (this.questionId >>> 32)));
		return hash;
	}

	public MasterPK(long adminId, long studentPrn, long testId, long questionId) {
		super();
		this.adminId = adminId;
		this.studentPrn = studentPrn;
		this.testId = testId;
		this.questionId = questionId;
	}
	
}