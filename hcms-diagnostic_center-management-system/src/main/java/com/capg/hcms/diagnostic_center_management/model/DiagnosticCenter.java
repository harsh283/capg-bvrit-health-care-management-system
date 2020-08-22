package com.capg.hcms.diagnostic_center_management.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="center")
public class DiagnosticCenter {
	
@Id
private String centerId;
private String centerName;
@ElementCollection
private List<String> tests=new ArrayList<>();
@ElementCollection
private List<BigInteger> appointments=new ArrayList<>();
public DiagnosticCenter() {
	super();
}
public DiagnosticCenter(String centerId, String centerName, List<String> testList, List<BigInteger> appointments) {
	super();
	this.centerId = centerId;
	this.centerName = centerName;
	this.tests = testList;
	this.appointments = appointments;
}
public String getCenterId() {
	return centerId;
}
public void setCenterId(String centerId) {
	this.centerId = centerId;
}
public String getCenterName() {
	return centerName;
}

public List<String> getTests() {
	return tests;
}
public void setTests(List<String> tests) {
	this.tests = tests;
}
public void setCenterName(String centerName) {
	this.centerName = centerName;
}

public List<BigInteger> getAppointments() {
	return appointments;
}
public void setAppointments(List<BigInteger> appointments) {
	this.appointments = appointments;
}
@Override
public String toString() {
	return "DiagnosticCenter [centerId=" + centerId + ", centerName=" + centerName + ", testList=" + tests
			+ ", appointments=" + appointments + "]";
}

}
