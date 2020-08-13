package com.capg.hcms.test_management.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.hcms.test_management.model.TestManagement;

@Repository
public interface TestRepo extends JpaRepository<TestManagement,String>{

	
	
}