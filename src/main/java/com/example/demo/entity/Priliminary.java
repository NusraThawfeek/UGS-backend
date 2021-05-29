package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
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
	
}
