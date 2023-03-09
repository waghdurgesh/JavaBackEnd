package com.app.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminSignupDto {
	@NotEmpty(message = "emailid cannot be null")
	private String adminEmail;
	@NotEmpty(message = "Firstname cannot be null")
	private String adminFirstname;
	@NotEmpty(message = "Lastname cannot be null")
	private String adminLastname;
	@NotEmpty(message = "Password cannot be null")
	private String adminPassword;

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminFirstname() {
		return adminFirstname;
	}

	public void setAdminFirstname(String adminFirstname) {
		this.adminFirstname = adminFirstname;
	}

	public String getAdminLastname() {
		return adminLastname;
	}

	public void setAdminLastname(String adminLastname) {
		this.adminLastname = adminLastname;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}


}
