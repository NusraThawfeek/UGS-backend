package com.example.demo.dto.response;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
