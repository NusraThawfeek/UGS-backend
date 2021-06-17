package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class CommentKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "FAC_Id")
	private long uid;
	
	@Column(name = "rid")
	private int rid;

	public CommentKey(Long long1, int rid) {
		super();
		this.uid = long1;
		this.rid = rid;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (uid ^ (uid >>> 32));
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
		if (uid != other.uid)
			return false;
		if (rid != other.rid)
			return false;
		return true;
	}


	public long getUid() {
		return uid;
	}


	public void setUid(long uid) {
		this.uid = uid;
	}


	public int getRid() {
		return rid;
	}


	public void setRid(int rid) {
		this.rid = rid;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
