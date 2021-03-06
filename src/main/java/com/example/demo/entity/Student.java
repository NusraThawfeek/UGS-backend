package com.example.demo.entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "indexNo") })
public class Student extends User {
	// index no is unique for everyone
	@NotBlank
	private String indexNo;
	

	@NotBlank
	private String batchYear;
	@NotBlank
	private String courseTitle;
	private String levelSemester;

	private int shortTermBal;
	
	private int longTermBal;
	
	@JsonIgnore
	@OneToMany(mappedBy="std",fetch = FetchType.LAZY)
	private Set<Request> req;

	@JoinColumn(name="academicAdvisor")
	@ManyToOne(fetch = FetchType.EAGER)
	private FACMember academicAdvisor;

	public String getIndexNo() {
		return indexNo;
	}

	public void setIndexNo(String indexNo) {
		this.indexNo = indexNo;
	}

	public String getBatchYear() {
		return batchYear;
	}

	public void setBatchYear(String batchYear) {
		this.batchYear = batchYear;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getLevelSemester() {
		return levelSemester;
	}

	public void setLevelSemester(String levelSemester) {
		this.levelSemester = levelSemester;
	}

	public int getShortTermBal() {
		return shortTermBal;
	}

	public void setShortTermBal(int shortTermBal) {
		this.shortTermBal = shortTermBal;
	}

	public int getLongTermBal() {
		return longTermBal;
	}

	public void setLongTermBal(int longTermBal) {
		this.longTermBal = longTermBal;
	}

	public Set<Request> getReq() {
		return req;
	}

	public void setReq(Set<Request> req) {
		this.req = req;
	}

	public FACMember getAcademicAdvisor() {
		return academicAdvisor;
	}

	public void setAcademicAdvisor(FACMember academicAdvisor) {
		this.academicAdvisor = academicAdvisor;
	}
	
	
}
