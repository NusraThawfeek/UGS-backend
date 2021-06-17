package com.example.demo.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
	
	private String contactNo;
	
	
	@NotBlank
	private String indexNo;
	@NotBlank
	private String batchYear;
	@NotBlank
	private String courseTitle;
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
	public String getNameToBeAppeared() {
		return nameToBeAppeared;
	}
	public void setNameToBeAppeared(String nameToBeAppeared) {
		this.nameToBeAppeared = nameToBeAppeared;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getIndexNo() {
		return indexNo;
	}
	public void setIndexNo(String indexNo) {
		this.indexNo = indexNo;
	}
	public String getBatchYear() {
		return batchYear;
	}
	public void setBatchYear(String batchYear) {
		this.batchYear = batchYear;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	
	
}