package com.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

/**
 * The persistent class for the batch_tbl database table.
 * 
 */
@Entity
@Table(name = "batch_tbl")
//@NoArgsConstructor
//@Getter
//@Setter
@ToString(exclude = "studentTbls")
@NamedQuery(name = "Batch.findAll", query = "SELECT b FROM Batch b")
public class Batch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "batch_id", unique = true, nullable = false)
	private long batchId;

	@Column(name = "batch_name", nullable = false, length = 255)
	private String batchName;

	@Temporal(TemporalType.DATE)
	@Column(name = "batch_created", nullable = false)
	private Date batchCreated;

	@Column(name = "batch_description", length = 1000)
	private String batchDescription;

	// bi-directional many-to-one association to Student
	@OneToMany(mappedBy = "batchTbl", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("batchTbl")
//	 @JsonBackReference
	private List<Student> studentTbls;

	public Batch() {
	}

	public long getBatchId() {
		return this.batchId;
	}

	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}

	public Date getBatchCreated() {
		return this.batchCreated;
	}

	public void setBatchCreated(Date batchCreated) {
		this.batchCreated = batchCreated;
	}

	public String getBatchDescription() {
		return this.batchDescription;
	}

	public void setBatchDescription(String batchDescription) {
		this.batchDescription = batchDescription;
	}

	public String getBatchName() {
		return this.batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public List<Student> getStudentTbls() {
		return this.studentTbls;
	}

	public void setStudentTbls(List<Student> studentTbls) {
		this.studentTbls = studentTbls;
	}

	public Student addStudentTbl(Student studentTbl) {
		getStudentTbls().add(studentTbl);
		studentTbl.setBatchTbl(this);

		return studentTbl;
	}

	public Student removeStudentTbl(Student studentTbl) {
		getStudentTbls().remove(studentTbl);
		studentTbl.setBatchTbl(null);

		return studentTbl;
	}

}