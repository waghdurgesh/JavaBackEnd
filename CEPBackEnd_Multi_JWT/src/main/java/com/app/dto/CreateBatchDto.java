package com.app.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBatchDto {
	private long batchId;
	private Date batchCreated;
	private String batchName;
	private String batchDescription;
}
