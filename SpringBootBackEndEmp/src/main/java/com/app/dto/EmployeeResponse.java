package com.app.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeResponse {
	private String firstname;
	private String lastname;

	public EmployeeResponse(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}

}
