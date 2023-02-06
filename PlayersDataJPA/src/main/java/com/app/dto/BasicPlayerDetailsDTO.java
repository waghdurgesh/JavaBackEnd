package com.app.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BasicPlayerDetailsDTO {
	private String firstname;
	private String lastname;
	private LocalDate dob;
	private int ranking;

	public BasicPlayerDetailsDTO(String firstname, String lastname, LocalDate dob, int ranking) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.ranking = ranking;
	}

}
