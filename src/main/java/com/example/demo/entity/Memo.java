package com.example.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Memo {

	@Id
	@GeneratedValue
	private int mid;
	private String title;
	private String description1;
	private String annexPath;
	private Date enteredDate = new Date();
	private String decision;
	private boolean isSendToFacMeeting;
	private boolean isAccepted;
	private boolean isRejected;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private FACMember facMember;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private FACMeeting facMeeting; 
	

	public Memo(String description1, String title, String annexPath, Date enteredDate, FACMember facMember, String decision) {
		super();
		this.title = title;
		this.description1 = description1;
		this.annexPath = annexPath;
		this.enteredDate = enteredDate;
		this.facMember = facMember;
		this.decision  = decision;
		this.isSendToFacMeeting = false;
		this.isAccepted=false;
		this.isRejected=false;
		
	}

	public Memo() {
		super();
	}

	public boolean getIsRejected() {
		return isRejected;
	}

	public void setIsRejected(boolean isRejected) {
		this.isRejected = isRejected;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSendToFacMeeting(boolean isSendToFacMeeting) {
		this.isSendToFacMeeting = isSendToFacMeeting;
	}

	public String getDescription1() {
		return description1;
	}

	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	public String getAnnexPath() {
		return annexPath;
	}

	public void setAnnexPath(String annexPath) {
		this.annexPath = annexPath;
	}

	public Date getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	public FACMember getFacMember() {
		return facMember;
	}

	public void setFacMember(FACMember facMember) {
		this.facMember = facMember;
	}

	public FACMeeting getFacMeeting() {
		return facMeeting;
	}

	public void setFacMeeting(FACMeeting facMeeting) {
		this.facMeeting = facMeeting;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public boolean getIsSendToFacMeeting() {
		return isSendToFacMeeting;
	}

	public void setIsSendToFacMeeting(boolean isSendToFacMeeting) {
		this.isSendToFacMeeting = isSendToFacMeeting;
	}

	public boolean getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
	
	
}
