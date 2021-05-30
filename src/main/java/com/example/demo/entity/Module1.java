package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Module1 {

	@Id
	private String mcode;
	private String mtitle;
	private double credits;
	private String dep;
	private String semester;
	private String degreeProgramme;
	
	@OneToMany(mappedBy = "module")
    private List<LateChanges> lateModuleChangeRequest;
	
	@OneToMany(mappedBy = "module")
    private List<LateChanges> alterModuleRequest;

	public Module1(String mcode, String mtitle, double credit, String dep) {
		super();
		this.mcode = mcode;
		this.mtitle = mtitle;
		this.credits = credit;
		this.dep = dep;
	}

	public Module1() {
		super();
	}

	public String getMcode() {
		return mcode;
	}

	public void setMcode(String mcode) {
		this.mcode = mcode;
	}

	public String getMtitle() {
		return mtitle;
	}

	public void setMtitle(String mtitle) {
		this.mtitle = mtitle;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
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
