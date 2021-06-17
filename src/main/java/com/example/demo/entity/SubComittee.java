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

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "subcommittees")
public class SubComittee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String purpose;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date appointdate;

    private String subcomitteefileName;

    private String subcomitteeStatus = "pending";
    @ManyToOne
    @JoinColumn(name = "meetingId")
    private FACMeeting facmeeting;

    @OneToOne
    @JoinColumn(name = "chairperson_id")
    private FACMember facmember;

    @ManyToOne(cascade = CascadeType.ALL)
    private FACMeeting createdFacMeeting;

    @ManyToOne(cascade = CascadeType.ALL)
    private FACMeeting reportSubmittedFacMeeting;

    public SubComittee() {
        super();
    }

    public SubComittee(String subcomitteeStatus, String purpose, String title, Date appointdate, String subcomitteefileName,
                       String type, byte[] data, FACMeeting facmeeting, FACMember facmember) {
        super();
        this.purpose = purpose;
        this.title = title;
        this.appointdate = appointdate;
        this.subcomitteefileName = subcomitteefileName;
        this.subcomitteeStatus = subcomitteeStatus;
        this.facmember = facmember;
        this.facmeeting = facmeeting;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public FACMeeting getFacmeeting() {
		return facmeeting;
	}

	public void setFacmeeting(FACMeeting facmeeting) {
		this.facmeeting = facmeeting;
	}

	public FACMember getFacmember() {
		return facmember;
	}

	public void setFacmember(FACMember facmember) {
		this.facmember = facmember;
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
