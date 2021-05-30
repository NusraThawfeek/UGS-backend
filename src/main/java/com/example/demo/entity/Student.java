package com.example.demo.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "indexNo") })
public class Student extends User {
	// index no is unique for everyone
	@NotBlank
	private String indexNo;
	
//	B18,B19,......
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
}
