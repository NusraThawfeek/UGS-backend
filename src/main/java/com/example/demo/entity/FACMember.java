package com.example.demo.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
public class FACMember extends User {

    @NotBlank
    private String title;

    @NotBlank
    private String department;
    private String lectureGradeLevel;


    @JsonProperty("isAcademicAdvisor")
    private boolean isAcademicAdvisor;

    @JsonProperty("isHod")
    private boolean isHod;

    @JsonProperty("isDean")
    private boolean isDean;

    @JsonProperty("isDugs")
    private boolean isDugs;

    @JsonProperty("isOnlyLecturer")
    private boolean isOnlyLecturer;

    @JsonIgnore
    @OneToMany(mappedBy = "uid", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Commented> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "academicAdvisor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> sid;

    private int extCode;

    public FACMember() {
        this.isAcademicAdvisor = false;
        this.isDean = false;
        this.isHod = false;
        this.isOnlyLecturer = true;

    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLectureGradeLevel() {
		return lectureGradeLevel;
	}

	public void setLectureGradeLevel(String lectureGradeLevel) {
		this.lectureGradeLevel = lectureGradeLevel;
	}

	public boolean isAcademicAdvisor() {
		return isAcademicAdvisor;
	}

	public void setAcademicAdvisor(boolean isAcademicAdvisor) {
		this.isAcademicAdvisor = isAcademicAdvisor;
	}

	public boolean isHod() {
		return isHod;
	}

	public void setHod(boolean isHod) {
		this.isHod = isHod;
	}

	public boolean isDean() {
		return isDean;
	}

	public void setDean(boolean isDean) {
		this.isDean = isDean;
	}

	public boolean isDugs() {
		return isDugs;
	}

	public void setDugs(boolean isDugs) {
		this.isDugs = isDugs;
	}

	public boolean isOnlyLecturer() {
		return isOnlyLecturer;
	}

	public void setOnlyLecturer(boolean isOnlyLecturer) {
		this.isOnlyLecturer = isOnlyLecturer;
	}

	public List<Commented> getComments() {
		return comments;
	}

	public void setComments(List<Commented> comments) {
		this.comments = comments;
	}

	public List<Student> getSid() {
		return sid;
	}

	public void setSid(List<Student> sid) {
		this.sid = sid;
	}

	public int getExtCode() {
		return extCode;
	}

	public void setExtCode(int extCode) {
		this.extCode = extCode;
	}



}