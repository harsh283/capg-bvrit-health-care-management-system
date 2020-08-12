package com.capg.hcms.usermanagementsystem.model;

import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

public class Appointment {
	private String userId;
	private BigInteger appointmentId;
	private String testId;
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dateTime;
	private boolean approved = false;
	
	public Appointment() {
		super();
	}
	
	public Appointment(String userId, BigInteger appointmentId, String testId, LocalDateTime dateTime,
			boolean approved) {
		super();
		this.userId = userId;
		this.appointmentId = appointmentId;
		this.testId = testId;
		this.dateTime = dateTime;
		this.approved = approved;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public BigInteger getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(BigInteger appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	@Override
	public String toString() {
		return "Appointment [userId=" + userId + ", appointmentId=" + appointmentId + ", testId=" + testId
				+ ", dateTime=" + dateTime + ", approved=" + approved + "]";
	}
	
	

}
