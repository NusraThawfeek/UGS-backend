package com.example.demo.dto.request;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ForgotUsernameOrPasswordRequest {
	@NotNull
	@NotEmpty
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
