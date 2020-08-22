package com.capg.hcms.test_management.model;

import java.math.BigInteger;
import java.util.List;
public class DiagnosticCenter {
private String centerId;
private String centerName;
private List<String> tests;
private List<BigInteger> appointments;
public DiagnosticCenter() {
	super();
	// TODO Auto-generated constructor stub
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
