package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AttendKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "fac_member_id")
	private long facMemberId;
	
	@Column(name = "fac_meeting_id")
	private int facMeetingId;

	public AttendKey(Long long1, int facMeetingId) {
		super();
		this.facMemberId = long1;
		this.facMeetingId = facMeetingId;
	}

	public AttendKey() {
		super();
	}

	public long getFacMemberId() {
		return facMemberId;
	}

	public void setFacMemberId(long facMemberId) {
		this.facMemberId = facMemberId;
	}

	public int getFacMeetingId() {
		return facMeetingId;
	}

	public void setFacMeetingId(int facMeetingId) {
		this.facMeetingId = facMeetingId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + facMeetingId;
		result = prime * result + (int) (facMemberId ^ (facMemberId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AttendKey other = (AttendKey) obj;
		if (facMeetingId != other.facMeetingId)
			return false;
		if (facMemberId != other.facMemberId)
			return false;
		return true;
	}
	
}
