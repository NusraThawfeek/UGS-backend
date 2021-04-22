package com.example.demo.dto.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentSingleRegister {
	@NotBlank
	private String indexNumber;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String nameToBeAppeared;

	@NotBlank
	private String contactNumber;
	@NotBlank
	private String batch;
	@NotBlank
	private String course;
	@NotBlank
	private String levelSem;
	
	@NotBlank
	private String academicAdvisorEmail;
		
	private int shortTermBal;
	
	private int longTermBal;

}

