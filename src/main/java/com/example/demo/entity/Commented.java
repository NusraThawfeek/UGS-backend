package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Commented {

	@EmbeddedId
    private CommentKey cid;

    @ManyToOne
    @MapsId("FACId")
    @JoinColumn(name = "FAC_Id")
    private FACMember uid;

    @ManyToOne
    @MapsId("rid")
    @JoinColumn(name = "rid")
    @JsonBackReference
    private Request rid;
	@Temporal(TemporalType.DATE)
    private Date enteredDate = new Date();
  
	private boolean IsRecomended;
	private boolean IsForwarded;
	private boolean IsRejected;
	private String description;

	public Commented() {
	}

	public Commented(FACMember facMember, Request request, boolean isForwarded, boolean isRecommended, boolean isRejected,
					 Date enteredDate, String description) {
		super();
		this.cid = new CommentKey(facMember.getUserId(), request.getRid());
		this.uid = facMember;
		this.rid = request;
		this.IsForwarded = isForwarded;
		this.IsRecomended = isRecommended;
		this.IsRejected = isRejected;
		this.enteredDate = enteredDate;
		this.description = description;
	}


	public CommentKey getCid() {
		return cid;
	}

	public void setCid(CommentKey cid) {
		this.cid = cid;
	}

	public FACMember getUid() {
		return uid;
	}

	public void setUid(FACMember uid) {
		this.uid = uid;
	}

	public Request getRid() {
		return rid;
	}

	public void setRid(Request rid) {
		this.rid = rid;
	}

	public Date getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	public boolean isIsRecomended() {
		return IsRecomended;
	}

	public void setIsRecomended(boolean isRecomended) {
		IsRecomended = isRecomended;
	}

	public boolean isIsForwarded() {
		return IsForwarded;
	}

	public void setIsForwarded(boolean isForwarded) {
		IsForwarded = isForwarded;
	}

	public boolean isIsRejected() {
		return IsRejected;
	}

	public void setIsRejected(boolean isRejected) {
		IsRejected = isRejected;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    
}
