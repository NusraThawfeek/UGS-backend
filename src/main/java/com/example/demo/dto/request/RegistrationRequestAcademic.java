package com.example.demo.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RegistrationRequestAcademic {
	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;
	
	@NotBlank
	@Email
	private String email;

	private String contactNumber;
	@NotBlank
	private String nameToBeAppeared;
	
	private String title;
}
