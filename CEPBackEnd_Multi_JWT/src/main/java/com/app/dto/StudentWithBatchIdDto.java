package com.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentWithBatchIdDto {
	private long stdPrn;
	private String stdEmail;
	private String stdFirstname;
	private String stdLastname;
	private String stdPassword;
	private String stdRollno;
	private long stdBatchId;

	public StudentWithBatchIdDto() {

	}

	public StudentWithBatchIdDto(long stdPrn, String stdEmail, String stdFirstname, String stdLastname,
			String stdPassword, String stdRollno, long stdBatchId) {
		super();
		this.stdPrn = stdPrn;
		this.stdEmail = stdEmail;
		this.stdFirstname = stdFirstname;
		this.stdLastname = stdLastname;
		this.stdPassword = stdPassword;
		this.stdRollno = stdRollno;
		this.stdBatchId = stdBatchId;
	}
}
