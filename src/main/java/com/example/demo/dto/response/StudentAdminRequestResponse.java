package com.example.demo.dto.response;

import lombok.Data;

@Data
public class StudentAdminRequestResponse {
	private String firstName;
	private String lastName;
	private String nameToBeAppeared;
	private String indexNumber;
	private String email;
	private String batch;
	private String contactNumber;
	private String course;
}
