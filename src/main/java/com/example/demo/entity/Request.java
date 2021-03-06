package com.example.demo.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rid;
	private String annexPath;
	private String decision;
	@Temporal(TemporalType.DATE)
	private Date enteredDate = new Date();
	private boolean isSendToFacBoard;
	private String type;
	private String status;

	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="std_user_id",nullable = false)
	private Student std;
	
	@OneToMany(mappedBy = "rid")
	@JsonManagedReference
	private List<Commented> comments;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private FACMeeting facMeeting1;

	public Request(String annexPath, String decision, Date enteredDate, boolean isSendToFacBoard,
			Student std, String type) {
		super();
		this.annexPath = annexPath;
		this.decision = decision;
		this.enteredDate = enteredDate;
		this.isSendToFacBoard = isSendToFacBoard;
		this.std = std;
		this.type = type;
	
	}

	public Request() {
		super();
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getAnnexPath() {
		return annexPath;
	}

	public void setAnnexPath(String annexPath) {
		this.annexPath = annexPath;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public Date getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	public boolean getIsSendToFacBoard() {
		return isSendToFacBoard;
	}

	public void setIsSendToFacBoard(boolean isSendToFacBoard) {
		this.isSendToFacBoard = isSendToFacBoard;
	}

	public Student getStd() {
		return std;
	}

	public void setStd(Student std) {
		this.std = std;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Commented> getComments() {
		return comments;
	}

	public void setComments(List<Commented> comments) {
		this.comments = comments;
	}

	public FACMeeting getFacMeeting1() {
		return facMeeting1;
	}

	public void setFacMeeting1(FACMeeting facMeeting1) {
		this.facMeeting1 = facMeeting1;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	

}
