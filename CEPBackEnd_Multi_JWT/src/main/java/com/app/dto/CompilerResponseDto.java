package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompilerResponseDto {

	// {"timeStamp":1678211094696,"status":200,"output":"Hello
	// Boys!123gijl","error":""}

	public long timeStamp;
	public long status;
	public String output;
	public String error;
}
