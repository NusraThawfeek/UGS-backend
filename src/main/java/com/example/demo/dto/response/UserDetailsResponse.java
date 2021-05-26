package com.example.demo.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class UserDetailsResponse {
	private String token;
	
	  private Long id; private String username; private String email;
	 
	private List<String> roles;

	public UserDetailsResponse(String accessToken, Long id, String email, List<String> roles) {
		this.token = accessToken;	
		  this.id = id; this.username = email; this.email = email;
		this.roles = roles;
	}

	public UserDetailsResponse() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/*
	 * public Long getId() { return id; }
	 * 
	 * public void setId(Long id) { this.id = id; }
	 * 
	 * public String getUsername() { return username; }
	 * 
	 * public void setUsername(String username) { this.username = username; }
	 * 
	 * public String getEmail() { return email; }
	 * 
	 * public void setEmail(String email) { this.email = email; }
	 */

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
