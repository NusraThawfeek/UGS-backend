package com.example.demo.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
	
	
	
	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getContactNumber() {
		return contactNumber;
	}



	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}



	public String getNameToBeAppeared() {
		return nameToBeAppeared;
	}



	public void setNameToBeAppeared(String nameToBeAppeared) {
		this.nameToBeAppeared = nameToBeAppeared;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	private String title;
}
