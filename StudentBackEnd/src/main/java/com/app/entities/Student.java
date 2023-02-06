package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "student")
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString(callSuper = true, exclude = "password")
public class Student extends BaseEntity {
	@Column(length = 20)
	private String firstname;
	@Column(length = 20)
	private String lastname;
	@Column(length = 20)
	private int rollno;
	@Column(length = 20, unique = true, nullable = true)
	private String email;
	@Column(length = 20, nullable = true)
	private String password;
	@Column(length = 20)
	private String location;
	private LocalDate dob;

	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(String firstname, String lastname, int rollno, String email, String password, String location,
			LocalDate dob) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.rollno = rollno;
		this.email = email;
		this.password = password;
		this.location = location;
		this.dob = dob;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getRollno() {
		return rollno;
	}

	public void setRollno(int rollno) {
		this.rollno = rollno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Student [firstname=" + firstname + ", lastname=" + lastname + ", rollno=" + rollno + ", email=" + email
				+ ", location=" + location + ", dob=" + dob + "]";
	}

}
