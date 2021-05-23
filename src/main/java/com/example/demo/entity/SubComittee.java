package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "subcommittees")
public class SubComittee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String purpose;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date appointdate;

	private String subcomitteefileName;
	
	//@Column(columnDefinition= "varchar(50) default 'pending'")
	private String subcomitteeStatus="pending";
	@ManyToOne
	@JoinColumn(name = "meetingId")
	private FACMeeting facmeeting;

	@OneToOne
	@JoinColumn(name = "chairperson_id")
	private FACMember facmember;

	public SubComittee() {
		super();
	}

	@ManyToOne(cascade = CascadeType.ALL)
	private FACMeeting createdFacMeeting;

	@ManyToOne(cascade = CascadeType.ALL)
	private FACMeeting reportSubmittedFacMeeting;

	/*
	 * @ManyToOne(cascade = CascadeType.ALL) private FACMember chairperson;
	 */

//	@ManyToMany()
//	@JoinTable(name = "subcommitee_participants", joinColumns = @JoinColumn(), inverseJoinColumns = @JoinColumn())
//	private List<FACMember> facmember1;

	public SubComittee(String subcomitteeStatus, String purpose, Date appointdate, String subcomitteefileName,
			String type, byte[] data, FACMeeting facmeeting,FACMember facmember) {
		this.purpose = purpose;

		this.appointdate = appointdate;
		this.subcomitteefileName = subcomitteefileName;
		this.subcomitteeStatus = subcomitteeStatus;
		this.facmember = facmember;
		this.facmeeting = facmeeting;

	}

	/*
	 * public SubComittee(String purpose, String discripition1, Date appointedDate,
	 * FACMember leader, List<FACMember> facmember) { super(); this.purpose =
	 * purpose; this.discripition1 = discripition1; this.appointedDate =
	 * appointedDate; this.facmember = facmember; this.leader = leader; }
	 */

	public String getPurpose() {
		return purpose;
	}

	public FACMember getFacmember() {
		return facmember;
	}

	public void setFacmember(FACMember facmember) {
		this.facmember = facmember;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getAppointdate() {
		return appointdate;
	}

	public void setAppointdate(Date appointdate) {
		this.appointdate = appointdate;
	}

	public String getSubcomitteefileName() {
		return subcomitteefileName;
	}

	public void setSubcomitteefileName(String subcomitteefileName) {
		this.subcomitteefileName = subcomitteefileName;
	}

	public String getSubcomitteeStatus() {
		return subcomitteeStatus;
	}

	public void setSubcomitteeStatus(String subcomitteeStatus) {
		this.subcomitteeStatus = subcomitteeStatus;
	}

	/*
	 * public FACMember getChairperson() { return chairperson; }
	 * 
	 * public void setChairperson(FACMember chairperson) { this.chairperson =
	 * chairperson; }
	 */

	public FACMeeting getFacmeeting() {
		return facmeeting;
	}

	public void setFacmeeting(FACMeeting facmeeting) {
		this.facmeeting = facmeeting;
	}

	public FACMeeting getCreatedFacMeeting() {
		return createdFacMeeting;
	}

	public void setCreatedFacMeeting(FACMeeting createdFacMeeting) {
		this.createdFacMeeting = createdFacMeeting;
	}

	public FACMeeting getReportSubmittedFacMeeting() {
		return reportSubmittedFacMeeting;
	}

	public void setReportSubmittedFacMeeting(FACMeeting reportSubmittedFacMeeting) {
		this.reportSubmittedFacMeeting = reportSubmittedFacMeeting;
	}

}
