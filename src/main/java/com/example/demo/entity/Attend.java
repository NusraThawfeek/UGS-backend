package com.example.demo.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Attend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "meetingId")
    private FACMeeting facmeeting;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private FACMember facmember;

    private String attendence;
    private String apology;

    public Attend() {

    }

    public Attend(Integer id, FACMember facmember, FACMeeting facmeeting, String attendence, String apology) {
        super();
        this.id = id;
        this.facmember = facmember;
        this.facmeeting = facmeeting;
        this.attendence = attendence;
        this.apology = apology;
    }


    public void addAtrribute(String string, List<Attend> listAllattendance) {
        // TODO Auto-generated method stub

    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getAttendence() {
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
