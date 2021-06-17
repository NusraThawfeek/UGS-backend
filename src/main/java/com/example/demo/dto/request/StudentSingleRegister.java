package com.example.demo.dto.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


import lombok.NoArgsConstructor;

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

	public String getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}

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

	public String getNameToBeAppeared() {
		return nameToBeAppeared;
	}

	public void setNameToBeAppeared(String nameToBeAppeared) {
		this.nameToBeAppeared = nameToBeAppeared;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getLevelSem() {
		return levelSem;
	}

	public void setLevelSem(String levelSem) {
		this.levelSem = levelSem;
	}

	public String getAcademicAdvisorEmail() {
		return academicAdvisorEmail;
	}

	public void setAcademicAdvisorEmail(String academicAdvisorEmail) {
		this.academicAdvisorEmail = academicAdvisorEmail;
	}

	public int getShortTermBal() {
		return shortTermBal;
	}

	public void setShortTermBal(int shortTermBal) {
		this.shortTermBal = shortTermBal;
	}

	public int getLongTermBal() {
		return longTermBal;
	}

	public void setLongTermBal(int longTermBal) {
		this.longTermBal = longTermBal;
	}

	
}

