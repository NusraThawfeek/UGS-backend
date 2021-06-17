package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Priliminary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "meetingId")
	private FACMeeting facmeeting;
	
	private String priliminary;

	public Priliminary(FACMeeting facmeeting, String priliminary) {
		super();
		this.facmeeting = facmeeting;
		this.priliminary = priliminary;
	}
	public Priliminary() {
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public FACMeeting getFacmeeting() {
		return facmeeting;
	}
	public void setFacmeeting(FACMeeting facmeeting) {
		this.facmeeting = facmeeting;
	}
	public String getPriliminary() {
		return priliminary;
	}
	public void setPriliminary(String priliminary) {
		this.priliminary = priliminary;
	}
	
	
	
}
