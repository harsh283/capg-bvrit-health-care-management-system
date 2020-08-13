package com.capg.hcms.diagnostic_center_management.service;

import java.util.List;

import com.capg.hcms.diagnostic_center_management.model.DiagnosticCenter;

public interface IDiagnosticCenterService {
DiagnosticCenter addCenter(DiagnosticCenter center);
List<DiagnosticCenter> getAllCenters();
DiagnosticCenter getCenterById(String centerId);
boolean removeAllCenters();
boolean removeCenter(String centerId);
}
