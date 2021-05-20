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

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@NoArgsConstructor
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


	
	

    
}
