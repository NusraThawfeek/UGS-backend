package com.example.demo.entity;

import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "fac_meeting")
public class FACMeeting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date;
	private String meetingTime;

	private String location;
	private String AgendaLink;
	private String MinuteLink1;
	private String agendaItem;
	private String priliminaries;
	private String mattersAriseMeeting;
	private String deciForMatteds;
	private String decissionBy;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "facMeeting")
	@JsonIgnore
	private List<Memo> memos;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "facMeeting1")
	@JsonIgnore
	private List<Request> requests;

	@OneToMany(mappedBy = "facmeeting")
	@JsonIgnore
	private List<Attend> attends;
	

	@ManyToOne(cascade = CascadeType.ALL)
	private AssistentRegistrar assistantRegistrar;


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "createdFacMeeting")
	private List<SubComittee> createSubCommitee;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "reportSubmittedFacMeeting")
	private List<SubComittee> reportSubmittedSubCommitee;

	public FACMeeting(Date date, String meetingTime, String location) {
		super();
		this.date = date;
		this.location = location;
		this.meetingTime = meetingTime;
	}

	

}
