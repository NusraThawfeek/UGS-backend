package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;


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
public String getMatters() {
	return matters;
}
public void setMatters(String matters) {
	this.matters = matters;
}
public String getDecission() {
	return decission;
}
public void setDecission(String decission) {
	this.decission = decission;
}
public String getDecissionBy() {
	return decissionBy;
}
public void setDecissionBy(String decissionBy) {
	this.decissionBy = decissionBy;
}
public Boolean getAgenda() {
	return agenda;
}
public void setAgenda(Boolean agenda) {
	this.agenda = agenda;
}
public Boolean getMinute() {
	return minute;
}
public void setMinute(Boolean minute) {
	this.minute = minute;
}
	

}
