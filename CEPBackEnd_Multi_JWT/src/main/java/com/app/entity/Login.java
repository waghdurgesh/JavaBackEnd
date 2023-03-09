package com.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import com.app.pojos.User;
@Entity
@Table(name = "login")
@JsonIgnoreProperties({ "admin", "student" })

public class Login {

	@Id
	@Column(length = 100, unique = true, updatable = false, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@OneToOne(mappedBy = "login", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Admin admin;

	@OneToOne(mappedBy = "login", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Student student;

	public Login(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Login() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Login [username=" + username + ", password=" + password + "]";
	}

	public void addAdmin(Admin admin) {
		this.admin = admin;
		admin.setLogin(this);
	}

	public void addStudent(Student student) {
		this.student = student;
		student.setLogin(this);

	}
}
