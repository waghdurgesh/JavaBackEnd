package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "players")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true, exclude = "password")

public class Player extends BaseEntity {
	@Column(length = 20)
	private String firstname;
	@Column(length = 20)
	private String lastname;
	@Column(length = 20, nullable = false)
	private int runs;
	@Column(length = 20, nullable = false)
	private int ranking;
	private LocalDate dob;
	@Column(length = 50, unique = true, nullable = false)
	private String email;
	@Column(length = 10, unique = true, nullable = false)
	private String password;

	public Player(String firstname, String lastname, int runs, int ranking, LocalDate dob, String email,
			String password) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.runs = runs;
		this.ranking = ranking;
		this.dob = dob;
		this.email = email;
		this.password = password;
	}

}
