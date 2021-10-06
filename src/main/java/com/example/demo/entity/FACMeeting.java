package com.example.demo.entity;

import java.sql.Date;
import java.util.List;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;





@Entity
@Table(name = "fac_meeting")
public class FACMeeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy/mm/dd")
    private Date date;
    private String meetingTime;
    private String meetingLink;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facMeeting")
    @JsonIgnore
    private List<Memo> memos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facMeeting1")
    @JsonIgnore
    private List<Request> requests;

    @OneToMany(mappedBy = "facmeeting")
    @JsonIgnore
    private List<Attend> attends;


    @OneToMany(mappedBy = "facmeeting")
    @JsonIgnore
    private List<Matters> matters;


    @ManyToOne(cascade = CascadeType.ALL)
    private AssistentRegistrar assistantRegistrar;


    @OneToMany(mappedBy = "facmeeting")
    @JsonIgnore
    private List<Priliminary> priliminary;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdFacMeeting")
    private List<SubComittee> createSubCommitee;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportSubmittedFacMeeting")
    @JsonIgnore
    private List<SubComittee> reportSubmittedSubCommitee;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "location_id1", referencedColumnName = "id")
    private Location location;

	public FACMeeting() {
	}

	public FACMeeting(Date date, String meetingTime, String meetingLink, Location location) {
        super();
        this.date = date;
        this.meetingTime = meetingTime;
        this.meetingLink = meetingLink;
        this.location = location;
    }


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getMeetingTime() {
		return meetingTime;
	}


	public void setMeetingTime(String meetingTime) {
		this.meetingTime = meetingTime;
	}


	public String getMeetingLink() {
		return meetingLink;
	}


	public void setMeetingLink(String meetingLink) {
		this.meetingLink = meetingLink;
	}


	public List<Memo> getMemos() {
		return memos;
	}


	public void setMemos(List<Memo> memos) {
		this.memos = memos;
	}


	public List<Request> getRequests() {
		return requests;
	}


	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}


	public List<Attend> getAttends() {
		return attends;
	}


	public void setAttends(List<Attend> attends) {
		this.attends = attends;
	}


	public List<Matters> getMatters() {
		return matters;
	}


	public void setMatters(List<Matters> matters) {
		this.matters = matters;
	}


	public AssistentRegistrar getAssistantRegistrar() {
		return assistantRegistrar;
	}


	public void setAssistantRegistrar(AssistentRegistrar assistantRegistrar) {
		this.assistantRegistrar = assistantRegistrar;
	}


	public List<Priliminary> getPriliminary() {
		return priliminary;
	}


	public void setPriliminary(List<Priliminary> priliminary) {
		this.priliminary = priliminary;
	}


	public List<SubComittee> getCreateSubCommitee() {
		return createSubCommitee;
	}


	public void setCreateSubCommitee(List<SubComittee> createSubCommitee) {
		this.createSubCommitee = createSubCommitee;
	}


	public List<SubComittee> getReportSubmittedSubCommitee() {
		return reportSubmittedSubCommitee;
	}


	public void setReportSubmittedSubCommitee(List<SubComittee> reportSubmittedSubCommitee) {
		this.reportSubmittedSubCommitee = reportSubmittedSubCommitee;
	}


	public Location getLocation() {
		return location;
	}


	public void setLocation(Location location) {
		this.location = location;
	}

    
}
