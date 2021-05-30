package com.example.demo.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
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



}