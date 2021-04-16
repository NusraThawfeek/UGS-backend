package com.example.demo.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.SuperBuilder;


@Entity
@Table
@PrimaryKeyJoinColumn
@SuperBuilder
public class LeaveRequest extends Request {

	private String leaveType;
	private String purpose;
	private Date dateFrom = new Date();
	private Date dateTo = new Date();
	private int localDays;
	private int overseasDays;
	private int totalDays;
	private int semesterDays;
	private int vacationDays;
	private String reason;

	public LeaveRequest(String annexPath, String decision, Date enteredDate, boolean isSendToFacBoard, String leaveType,
			String purpose, Date dateFrom, Date dateTo, int localDays, int overseasDays, int totalDays,
			int semesterDays, int vacationDays, String reason, Student std, String type) {
		super(annexPath, decision, enteredDate, isSendToFacBoard, std, type);
		this.leaveType = leaveType;
		this.purpose = purpose;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.localDays = localDays;
		this.overseasDays = overseasDays;
		this.totalDays = totalDays;
		this.semesterDays = semesterDays;
		this.vacationDays = vacationDays;
		this.reason = reason;
	}

	public LeaveRequest(String annexPath, String decision, Date enteredDate, boolean isSendToFacBoard, Student std,
			String type) {
		super(annexPath, decision, enteredDate, isSendToFacBoard, std, type);
	}

	public LeaveRequest() {
		super();
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public int getLocalDays() {
		return localDays;
	}

	public void setLocalDays(int localDays) {
		this.localDays = localDays;
	}

	public int getOverseasDays() {
		return overseasDays;
	}

	public void setOverseasDays(int overseasDays) {
		this.overseasDays = overseasDays;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public int getSemesterDays() {
		return semesterDays;
	}

	public void setSemesterDays(int semesterDays) {
		this.semesterDays = semesterDays;
	}

	public int getVacationDays() {
		return vacationDays;
	}

	public void setVacationDays(int vacationDays) {
		this.vacationDays = vacationDays;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
