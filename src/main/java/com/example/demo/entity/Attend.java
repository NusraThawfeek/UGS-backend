package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;


@Data
@Entity
public class Attend {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "meetingId")
	private FACMeeting facmeeting;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "memberId")
	private FACMember facmember;

	private String attendence;
	private String apology;



	public Attend(Integer id, FACMember facmember, FACMeeting facmeeting, String attendence, String apology) {
		super();
		this.id = id;
		this.facmember = facmember;
		this.facmeeting = facmeeting;
		this.attendence = attendence;
		this.apology = apology;
	}
public Attend() {
		
	}

	public void addAtrribute(String string, List<Attend> listAllattendance) {
		// TODO Auto-generated method stub

	}
}
