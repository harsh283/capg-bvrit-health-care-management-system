package com.capg.hcms.diagnostic_center_management.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.hcms.diagnostic_center_management.model.DiagnosticCenter;

public interface DiagnosticCenterRepo extends JpaRepository<DiagnosticCenter, String> {

}
