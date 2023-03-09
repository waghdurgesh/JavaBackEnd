package com.app.dto;

public class LogedInUserDto {

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserFirstname() {
		return userFirstname;
	}

	public void setUserFirstname(String userFirstname) {
		this.userFirstname = userFirstname;
	}

	public String getUserLastname() {
		return userLastname;
	}

	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	private Long userId;
	private String userEmail;
	private String userFirstname;
	private String userLastname;
	private String username;
	private String userrole;

	public LogedInUserDto() {

	}

	public LogedInUserDto(Long userId, String userEmail, String userFirstname, String userLastname, String username,
			String userrole) {
		super();
		this.userId = userId;
		this.userEmail = userEmail;
		this.userFirstname = userFirstname;
		this.userLastname = userLastname;
		this.username = username;
		this.userrole = userrole;
	}

}
