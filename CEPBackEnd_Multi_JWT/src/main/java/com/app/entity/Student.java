package com.app.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

/**
 * The persistent class for the student_tbl database table.
 * 
 */
@Entity
@Table(name = "student_tbl")
//@NoArgsConstructor
//@Getter
//@Setter
@ToString(exclude = "stdPassword")
@NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "std_prn", unique = true, nullable = false)
	private long stdPrn;

	@Column(name = "std_rollno", unique = true, nullable = false, length = 255)
	private String stdRollno;

	@Column(name = "std_firstname", nullable = false, length = 255)
	private String stdFirstname;

	@Column(name = "std_lastname", nullable = false, length = 255)
	private String stdLastname;

	@Column(name = "std_email", unique = true, nullable = false, length = 255)
	private String stdEmail;

	@Column(name = "std_password", nullable = false, length = 255)
	private String stdPassword;

	public static final String role = "ROLE_STUDENT";

//	@Column(name = "std_role", columnDefinition = "varchar(20) default 'ROLE_STUDENT'", nullable = false, updatable = false)
//	private String studentRole;

	// bi-directional many-to-one association to Master
	@OneToMany(mappedBy = "studentTbl", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("studentTbl")
//	@JsonBackReference
	private List<Master> masterTbls;

	// bi-directional many-to-one association to Batch
	@ManyToOne
	@JoinColumn(name = "batch_id", nullable = false)
	@JsonIgnoreProperties("studentTbls")
//	@JsonBackReference
	private Batch batchTbl;

	@OneToOne
	@JoinColumn(name = "username", referencedColumnName = "username")
	private Login login;

	public Student() {
	}

	public long getStdPrn() {
		return this.stdPrn;
	}

	public void setStdPrn(long stdPrn) {
		this.stdPrn = stdPrn;
	}

	public String getStdEmail() {
		return this.stdEmail;
	}

	public void setStdEmail(String stdEmail) {
		this.stdEmail = stdEmail;
	}

	public String getStdFirstname() {
		return this.stdFirstname;
	}

	public void setStdFirstname(String stdFirstname) {
		this.stdFirstname = stdFirstname;
	}

	public String getStdLastname() {
		return this.stdLastname;
	}

	public void setStdLastname(String stdLastname) {
		this.stdLastname = stdLastname;
	}

	public String getStdPassword() {
		return this.stdPassword;
	}

	public void setStdPassword(String stdPassword) {
		this.stdPassword = stdPassword;
	}

	public String getStdRollno() {
		return this.stdRollno;
	}

	public void setStdRollno(String stdRollno) {
		this.stdRollno = stdRollno;
	}

	public List<Master> getMasterTbls() {
		return this.masterTbls;
	}

	public void setMasterTbls(List<Master> masterTbls) {
		this.masterTbls = masterTbls;
	}

	public Master addMasterTbl(Master masterTbl) {
		getMasterTbls().add(masterTbl);
		masterTbl.setStudentTbl(this);

		return masterTbl;
	}

	public Master removeMasterTbl(Master masterTbl) {
		getMasterTbls().remove(masterTbl);
		masterTbl.setStudentTbl(null);

		return masterTbl;
	}

	public Batch getBatchTbl() {
		return this.batchTbl;
	}

	public void setBatchTbl(Batch batchTbl) {
		this.batchTbl = batchTbl;
	}

	public Student(long stdPrn, String stdRollno, String stdFirstname, String stdLastname, String stdEmail,
			String stdPassword, Batch batchTbl) {
		super();
		this.stdPrn = stdPrn;
		this.stdRollno = stdRollno;
		this.stdFirstname = stdFirstname;
		this.stdLastname = stdLastname;
		this.stdEmail = stdEmail;
		this.stdPassword = stdPassword;
		this.batchTbl = batchTbl;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

//	public String getStudentRole() {
//		return studentRole;
//	}
//
//	public void setStudentRole(String studentRole) {
//		this.studentRole = studentRole;
//	}

	// constructor
//	public Student(long stdPrn, String stdEmail, String stdFirstname, String stdLastname, String stdPassword,
//			String stdRollno, Batch batchTbl) {
//		super();
//		this.stdPrn = stdPrn;
//		this.stdEmail = stdEmail;
//		this.stdFirstname = stdFirstname;
//		this.stdLastname = stdLastname;
//		this.stdPassword = stdPassword;
//		this.stdRollno = stdRollno;
//		this.batchTbl = batchTbl;
//	}

}