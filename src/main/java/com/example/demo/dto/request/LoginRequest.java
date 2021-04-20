package com.example.demo.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoginRequest {
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	
	
}
