package com.emp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.emp.model.LeaveRequest;

public interface LeaveRequestRepository extends MongoRepository<LeaveRequest, String> {
	Optional<LeaveRequest> findByEmployeeId(String employeeId);
//	List<LeaveRequest> findByApprovedpending();
//	List<LeaveRequest> findByApproved(String approved);
	List<LeaveRequest> findByStartdateContaining(String monthYear);
}
