package com.example.demo.dto.request;


import javax.validation.constraints.NotBlank;

public class AcadAdvisorBatchRequest {
	@NotBlank
	private String batch;
	@NotBlank
	private String indexNumber;
	@NotBlank
	private String acadAdvisorEmail;
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getIndexNumber() {
		return indexNumber;
	}
	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}
	public String getAcadAdvisorEmail() {
		return acadAdvisorEmail;
	}
	public void setAcadAdvisorEmail(String acadAdvisorEmail) {
		this.acadAdvisorEmail = acadAdvisorEmail;
	}
	
	
}
