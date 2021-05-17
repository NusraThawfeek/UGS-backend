package com.example.demo.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class StudentBatchRequest {
	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;
	
	@NotBlank
	private String nameToBeAppeared;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String contactNo;
	
	
	@NotBlank
	private String indexNo;
	@NotBlank
	private String batchYear;
	@NotBlank
	private String courseTitle;
}