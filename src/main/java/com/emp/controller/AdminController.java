package com.emp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emp.model.Admin;
import com.emp.model.Employee;
import com.emp.model.LeaveRequest;
import com.emp.service.EmployeeService;
import com.emp.service.LeaveRequestService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
	
	@Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveRequestService leaveRequestService;
    
    
    @PostMapping("/insertemp")
	public String createmp(@RequestBody Employee emp) {
    	employeeService.insert(emp);
		return "emp is inserted";
	} 
    
    
//	@GetMapping("/pending-requests")
//    public ResponseEntity<?> getPendingRequests() {
//        List<LeaveRequest> pendingRequests = leaveRequestService.findPendingRequests();
//        return ResponseEntity.ok(pendingRequests);
//    }
	
//	@GetMapping("/leave-details")
//    public ResponseEntity<?> getLeaveDetails(@RequestParam String id) {
//        Optional<Employee> employee = employeeService.findById(id);
//        if (employee.isPresent()) {
//            return ResponseEntity.ok(employee.get());
//        }
//        return ResponseEntity.badRequest().body("Employee not found");
//    }
	@GetMapping("/leave-details")
    public ResponseEntity<?> getLeaveDetails(@RequestParam String id) {
        Optional<LeaveRequest> employee = leaveRequestService.findByEmployeeId(id);
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        }
        return ResponseEntity.badRequest().body("Employee not found");
    }
	
	
	
//	@PostMapping("/approve")
//    public String approve(@RequestParam String empid, @RequestBody Admin app) {
//		Optional<Employee> empdet = employeeService.findById(empid);
//		Optional<LeaveRequest> leavedet = leaveRequestService.findByEmployeeId(empid);
//		Employee emp = empdet.get();
//		LeaveRequest lve = leavedet.get();
////		int leaves = emp.getTotalLeaves();
//        String ad = app.getApprove();
//        if(ad != null) {
////        	leaves -= 1;
//        	emp.setTotalLeaves(emp.getTotalLeaves()-1);
//        	employeeService.save(emp);
//        	lve.setApproved(ad);
//        	leaveRequestService.save(lve);
//            return "accepted";
//        } 
//        
//        else {
//        	lve.setApproved(ad);
//            return "rejected";
//        }
//    }
//	
//	
//	@PostMapping("/approve")
//	public String approve(@RequestParam String empid, @RequestBody Admin app) {
//	    Optional<Employee> empdet = employeeService.findById(empid);
//	    Optional<LeaveRequest> leavedet = leaveRequestService.findByEmployeeId(empid);
//
//	    if(empdet.isPresent() && leavedet.isPresent()) {
//	        Employee emp = empdet.get();
//	        LeaveRequest lve = leavedet.get();
//	        String ad = app.getApprove();
//
//	        if ("accepted".equalsIgnoreCase(ad)) {
//	            emp.setTotalLeaves(emp.getTotalLeaves()-1);
//	            employeeService.save(emp);
//	            lve.setApproved("accepted");
//	            leaveRequestService.save(lve);
//	            return "Leave request accepted";
//	        } else if ("rejected".equalsIgnoreCase(ad)) {
//	            lve.setApproved("rejected");
//	            leaveRequestService.save(lve);
//	            return "Leave request rejected";
//	        } else {
//	            return "Invalid approval status";
//	        }
//	    } else {
//	        return "Employee or LeaveRequest not found";
//	    }
//	}
	@GetMapping("/monthlyreport")
    public List<LeaveRequest> getMonthlyReport(@RequestParam String month, @RequestParam String year) {
        String monthYear = month + "-" + year; // e.g., "08-2024"
        return leaveRequestService.getMonthlyReport(monthYear);
    }

}
