//package com.emp.service;
//
//import java.util.List;
//import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.emp.model.LeaveRequest;
//import com.emp.repository.LeaveRequestRepository;
//
//@Service
//public class LeaveRequestService {
//
//    @Autowired
//    private LeaveRequestRepository leaveRequestRepository;
//
//    public LeaveRequest save(LeaveRequest leaveRequest) {
//        return leaveRequestRepository.save(leaveRequest);
//    }
//    
//    public List<LeaveRequest> findPendingRequests() {
//        return leaveRequestRepository.findByApprovedFalse();
//    }
//
//    public Optional<LeaveRequest> findById(String id) {
//        return leaveRequestRepository.findById(id);
//    }
//
//    public List<LeaveRequest> findAll() {
//        return leaveRequestRepository.findAll();
//    }
//}

package com.emp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.emp.model.LeaveRequest;
import com.emp.repository.LeaveRequestRepository;


@Service
public class LeaveRequestService {

    @Autowired
    public LeaveRequestRepository leaveRequestRepository;

//    public LeaveRequest save(LeaveRequest leaveRequest) {
//        return leaveRequestRepository.save(leaveRequest);
//    }
    
    public void save(LeaveRequest lve) {
    	leaveRequestRepository.save(lve);
    } 

//    public List<LeaveRequest> findPendingRequests() {
//        return leaveRequestRepository.findByApproved("pending");
//    }

//    public Optional<LeaveRequest> findById(String employeeid) {
//        return leaveRequestRepository.findById(employeeid);
//    }

    public List<LeaveRequest> findAll() {
        return leaveRequestRepository.findAll();
    }
    
    public void delete(LeaveRequest leaveRequest) {
        leaveRequestRepository.delete(leaveRequest);
    }
    
    public Optional<LeaveRequest> findByEmployeeId(String employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }
    public List<LeaveRequest> getMonthlyReport(String monthYear) {
        // Fetch leave requests that match the month and year
        return leaveRequestRepository.findByStartdateContaining(monthYear);
    }
}

