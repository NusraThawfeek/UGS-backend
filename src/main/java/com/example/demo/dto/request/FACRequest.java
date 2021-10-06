package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

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


	public int getExtCode() {
		return extCode;
	}


	public void setExtCode(int extCode) {
		this.extCode = extCode;
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


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public boolean isAcadAvisor() {
		return isAcadAvisor;
	}


	public void setAcadAvisor(boolean isAcadAvisor) {
		this.isAcadAvisor = isAcadAvisor;
	}
	
	
	
}
