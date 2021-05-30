package com.example.demo.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class FacAcademicRequest {
	@NotBlank
	private String email;
}
