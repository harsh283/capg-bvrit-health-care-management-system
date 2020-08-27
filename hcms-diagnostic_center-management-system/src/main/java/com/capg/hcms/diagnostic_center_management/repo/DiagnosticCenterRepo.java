/**
	* Project Name : Health Care Management System
	*
	* 
**/

package com.capg.hcms.diagnostic_center_management.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.hcms.diagnostic_center_management.model.DiagnosticCenter;
/**
* Interface DiagnosticCenter Repository  with DiagnosticCenter as Type and centerId as a key 
*
* @author   :Shambu Harsh Kumar
* @version  :1.0
* @since    :2020-08-14 
**/
public interface DiagnosticCenterRepo extends JpaRepository<DiagnosticCenter, String> {
	/**
	* Method existsByCenterName checks the existence of center by the same name  
	* @return   :boolean 
	* @author   :Shambu Harsh Kumar
	* @version  :1.0
	* @since    :2020-08-15 
	**/
	public boolean existsByCenterName(String centerName);
}
