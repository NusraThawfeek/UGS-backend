package com.example.demo.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class FACRequest {
	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;
	
	@NotBlank
	private String email;

	private String contactNumber;
	private int extCode;
	
	@NotBlank
	private String nameToBeAppeared;
	
	private String title;
	
	@NotBlank
	private String department;
	
	
	@NotBlank
	@JsonProperty("isAcadAvisor")
	private boolean isAcadAvisor;
}
