package com.example.demo.entity;

import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Attend {

	// @EmbeddedId
	// private AttendKey Id;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

//	@ManyToOne
//
//	@MapsId("facMemberId")
//
//	@JoinColumn(name = "fac_member_id")
//	private FACMember facmember;
//
//	@ManyToOne
//
//	@MapsId("facMeetingId")
//
//	@JoinColumn(name = "fac_meeting_id")
//	private FACMeeting facMeeting;
	@ManyToOne
	@JoinColumn(name = "meetingId")
	private FACMeeting facmeeting;

	@ManyToOne
	@JoinColumn(name = "memberId")
	private FACMember facmember;
	
	private String attendence;
	private String apology;

	public Attend(Integer id, FACMember facmember, FACMeeting facmeeting, String attendence, String apology) {
		super();
		// Id = new AttendKey(facMember.getUserId(), facMeeting.getId());
		this.id = id;
		this.facmember = facmember;
		this.facmeeting = facmeeting;
		this.attendence = attendence;
		this.apology = apology;
	}

	public Attend() {
		super();
	}

	public FACMember getFacMember() {
		return facmember;
	}

	public void setFacMember(FACMember facMember) {
		this.facmember = facMember;
	}

	public FACMeeting getFacMeeting() {
		return facmeeting;
	}

	public void setFacMeeting(FACMeeting facMeeting) {
		this.facmeeting = facMeeting;
	}

	public String getAttendence() {
		return attendence;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String isAttendence() {
		return attendence;
	}

	public void setAttendence(String attendence) {
		this.attendence = attendence;
	}

	public String getApology() {
		return apology;
	}

	public void setApology(String apology) {
		this.apology = apology;
	}

	

}
