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
public class Matters {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "meetingId")
	private FACMeeting facmeeting;
	
	private String matters;
	private String decission;
	private String decissionBy;
	private Boolean agenda;
	private Boolean minute;

	public Matters(FACMeeting facmeeting, String matters, String decission, String decissionBy) {
		super();
		this.facmeeting = facmeeting;
		this.matters = matters;
		this.decission = decission;
		this.decissionBy=decissionBy;
	}
	public Matters(FACMeeting facmeeting, String matters) {
		super();
		this.facmeeting = facmeeting;
		this.matters = matters;
	}
public Matters() {
		
	}
	
}
