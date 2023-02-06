package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Entity
@Table(name = "new_emps")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true, exclude = "password")
public class Employee extends BaseEntity {
	@Column(length = 20)
	private String firstname;
	@Column(length = 20)
	private String lastname;
	@Column(length = 30, unique = true,nullable = false)
	private String email;
	@Column(length = 20, nullable = false)
	private String password;
	@Column(length = 20)
	@JsonProperty(value = "location")
	private String workLocation;
	@Column(length = 20)
	private String department;
	private LocalDate joindate;
	private double salary;

	public Employee(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}

}
