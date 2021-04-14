package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CommentKey implements Serializable {

	@Column(name = "FAC_Id")
	private long FACId;
	
	@Column(name = "rid")
	private int rid;

	public CommentKey(Long long1, int rid) {
		super();
		FACId = long1;
		this.rid = rid;
	}

	public CommentKey() {
		super();
	}

	public long getFACId() {
		return FACId;
	}

	public void setFACId(long fACId) {
		FACId = fACId;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (FACId ^ (FACId >>> 32));
		result = prime * result + rid;
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
		CommentKey other = (CommentKey) obj;
		if (FACId != other.FACId)
			return false;
		if (rid != other.rid)
			return false;
		return true;
	}
}
