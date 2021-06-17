package com.example.demo.dto.request;

import javax.validation.constraints.NotBlank;

public class FacAcademicRequest {
	@NotBlank
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
