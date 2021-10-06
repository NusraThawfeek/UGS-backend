package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Module1 {

	@Id
	private String moduleCode;
	private String moduleName;
	private double credits;
	private String department;
	private String semester;
	private String degreeProgramme;
	
	@OneToMany(mappedBy = "module")
    private List<LateChanges> lateModuleChangeRequest;
	
	@OneToMany(mappedBy = "module")
    private List<LateChanges> alterModuleRequest;

	public Module1(String moduleCode, String moduleName, double credit, String department) {
		super();
		this.moduleCode = moduleCode;
		this.moduleName = moduleName;
		this.credits = credit;
		this.department = department;
	}

	public Module1() {
		super();
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String mcode) {
		this.moduleCode = mcode;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String mtitle) {
		this.moduleName = mtitle;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String dep) {
		this.department = dep;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public double getCredits() {
		return credits;
	}

	public void setCredits(double credits) {
		this.credits = credits;
	}

	public String getDegreeProgramme() {
		return degreeProgramme;
	}

	public void setDegreeProgramme(String degreeProgramme) {
		this.degreeProgramme = degreeProgramme;
	}
	
	
}
