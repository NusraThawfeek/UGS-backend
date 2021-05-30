package com.example.demo.dto.request;

import lombok.Data;

@Data
public class ModuleAdd {
	private String degreeProgramme;
	private String semester;
	private String department;
	private String moduleCode;
	private String moduleName;
	private double credits;
	
}
