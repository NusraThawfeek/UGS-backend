package com.example.demo.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AcadAdvisorBatchRequest {
	@NotBlank
	private String batch;
	@NotBlank
	private String indexNumber;
	@NotBlank
	private String acadAdvisorEmail;
	
	
}
