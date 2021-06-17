package com.example.demo.dto.request;

public class ModuleAdd {
	private String degreeProgramme;
	private String semester;
	private String department;
	private String moduleCode;
	private String moduleName;
	private double credits;
	public String getDegreeProgramme() {
		return degreeProgramme;
	}
	public void setDegreeProgramme(String degreeProgramme) {
		this.degreeProgramme = degreeProgramme;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public double getCredits() {
		return credits;
	}
	public void setCredits(double credits) {
		this.credits = credits;
	}
	
	
}
