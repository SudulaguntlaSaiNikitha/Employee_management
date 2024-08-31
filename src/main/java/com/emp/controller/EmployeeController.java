//package com.emp.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import com.emp.model.Employee;
//import com.emp.model.LeaveRequest;
//import com.emp.service.EmployeeService;
//import com.emp.service.LeaveRequestService;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/employee")
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    @Autowired
//    private LeaveRequestService leaveRequestService;
//
//    // Endpoint for user to submit leave request
//    @GetMapping("/leave-details")
//    public ResponseEntity<?> getLeaveDetails(@RequestParam String id) {
//        Optional<Employee> employee = employeeService.findById(id);
//        if (employee.isPresent()) {
//            return ResponseEntity.ok(employee.get());
//        }
//        return ResponseEntity.badRequest().body("Employee not found");
//    }
//    
//    @PostMapping("/apply-leave")
//    public ResponseEntity<?> applyLeave(@RequestBody LeaveRequest leaveRequest) {
//        // Check if employee exists
//        Optional<Employee> employeeOpt = employeeService.findById(leaveRequest.getEmployeeId());
//        if (employeeOpt.isPresent()) {
//            Employee employee = employeeOpt.get();
//            // Process the leave request
//            leaveRequest.setApproved(false); // Default value
//            LeaveRequest savedRequest = leaveRequestService.save(leaveRequest);
//            return ResponseEntity.ok("Leave request submitted successfully with ID: " + savedRequest.getId());
//        } else {
//            return ResponseEntity.badRequest().body("Employee not found");
//        }
//    }
//    
//    @GetMapping("/admin/pending-requests")
//    public ResponseEntity<?> getPendingRequests() {
//        List<LeaveRequest> pendingRequests = leaveRequestService.findPendingRequests();
//        return ResponseEntity.ok(pendingRequests);
//    }
//
//
//    // Endpoint for admin to approve or reject leave request
//    @PostMapping("/admins")
//    public ResponseEntity<?> handleLeaveRequest(@RequestParam String leaveRequestId, @RequestParam boolean approve) {
//        // Find the leave request by ID
//        Optional<LeaveRequest> leaveRequestOpt = leaveRequestService.findById(leaveRequestId);
//        if (leaveRequestOpt.isPresent()) {
//            LeaveRequest leaveRequest = leaveRequestOpt.get();
//
//            // Check if the leave request is already processed
////            if (leaveRequest.isApproved()) {
////                return ResponseEntity.badRequest().body("Leave request is already processed");
////            }
//
//            // Find the employee associated with the leave request
//            Optional<Employee> employeeOpt = employeeService.findById(leaveRequest.getEmployeeId());
//            if (employeeOpt.isPresent()) {
//                Employee employee = employeeOpt.get();
//                
//                if (approve) {
//                    // Approve the leave request
//                    leaveRequest.setApproved(true);
//                    leaveRequestService.save(leaveRequest);
//
//                    // Decrement the employee's leave balance
//                    if (employee.getTotalLeaves() > 0) {
//                        employee.setTotalLeaves(employee.getTotalLeaves() - 1);
//                        employeeService.save(employee);
//                        return ResponseEntity.ok("Leave request approved and leave balance updated");
//                    } else {
//                        return ResponseEntity.badRequest().body("Not enough leave balance");
//                    }
//                } else {
//                    // Reject the leave request
//                    leaveRequest.setApproved(false);
//                    leaveRequestService.save(leaveRequest);
//                    return ResponseEntity.ok("Leave request rejected by admin");
//                }
//            } else {
//                return ResponseEntity.badRequest().body("Employee not found");
//            }
//        } else {
//            return ResponseEntity.badRequest().body("Leave request not found");
//        }
//    }
//}

package com.emp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.emp.model.Employee;
import com.emp.model.LeaveRequest;
import com.emp.model.User;
import com.emp.service.EmployeeService;
import com.emp.service.LeaveRequestService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveRequestService leaveRequestService;

    // Endpoint for user to submit leave request
//    @GetMapping("/leave-details")
//    public ResponseEntity<?> getLeaveDetails(@RequestParam String id) {
//        Optional<Employee> employee = employeeService.findById(id);
//        if (employee.isPresent()) {
//            return ResponseEntity.ok(employee.get());
//        }
//        return ResponseEntity.badRequest().body("Employee not found");
//    }

    
//    @PostMapping("/insertemp")
//	public String createmp(@RequestBody Employee emp) {
//    	employeeService.insert(emp);
//		return "emp is inserted";
//	} 
//    @PostMapping("/insertleave")
//	public String createleave(@RequestBody LeaveRequest lve) {
//    	String leavetp = lve.getLeavetype();
//    	Optional<Employee> op = employeeService.findById(lve.getEmployeeId());
//    	Employee empdata = op.get();
//    	if(op.isPresent()) {
//    		if("cl".equalsIgnoreCase(leavetp)) {
//    			if(empdata.getCl() >= lve.getDays()) {
//    				lve.setPrev_cl(empdata.getCl());
//        			lve.setPrev_el(empdata.getEl());
//        			lve.setPrev_sl(empdata.getSl());
//        			empdata.setCl(empdata.getCl()-lve.getDays());
//        			lve.setUp_cl(empdata.getCl());
//        			lve.setUp_el(empdata.getEl());
//        			lve.setUp_sl(empdata.getSl());
//        			employeeService.save(empdata);
//        			leaveRequestService.save(lve);
//            		return "leave submitted and waiting for approval";
//        		}
//    			else {
//        			return "Insufficient leaves";
//        		}
//    		}
//    @PostMapping("/insertemp")
//    public String createleave(@RequestBody LeaveRequest lve) {
//        String leavetp = lve.getLeavetype();
//        Optional<Employee> op = employeeService.findById(lve.getEmployeeId());
//        if (op.isPresent()) {
//            Employee empdata = op.get();
//            LocalDate startDate = LocalDate.parse(lve.getStartdate(), DateTimeFormatter.ofPattern("dd-MM-yy"));
//            LocalDate endDate = LocalDate.parse(lve.getEnddate(), DateTimeFormatter.ofPattern("dd-MM-yy"));
//
//            if ("cl".equalsIgnoreCase(leavetp)) {
//                if (empdata.getCl() >= lve.getDays()) {
//                    lve.setPrev_cl(empdata.getCl());
//                    lve.setPrev_el(empdata.getEl());
//                    lve.setPrev_sl(empdata.getSl());
//
//                    // Check if the leave spans across months
//                    if (!startDate.getMonth().equals(endDate.getMonth())) {
//                        // Calculate days in the start month
//                        int daysInStartMonth = startDate.lengthOfMonth() - startDate.getDayOfMonth() + 1;
//
//                        // Adjust the CL for the start month
//                        empdata.setCl(empdata.getCl() - daysInStartMonth);
//                        lve.setUp_cl(empdata.getCl());
//                        lve.setUp_el(empdata.getEl());
//                        lve.setUp_sl(empdata.getSl());
//                        leaveRequestService.save(lve);
//
//                        // Create a new leave request for the remaining days in the next month
//                        LeaveRequest nextMonthLeave = new LeaveRequest();
//                        nextMonthLeave.setEmployeeId(lve.getEmployeeId());
//                        nextMonthLeave.setLeavetype(lve.getLeavetype());
//                        nextMonthLeave.setStartdate("01-" + endDate.format(DateTimeFormatter.ofPattern("MM-yy")));
//                        nextMonthLeave.setEnddate(endDate.format(DateTimeFormatter.ofPattern("dd-MM-yy")));
//                        nextMonthLeave.setDays(lve.getDays() - daysInStartMonth);
//                        nextMonthLeave.setPrev_cl(empdata.getCl());
//                        nextMonthLeave.setPrev_el(empdata.getEl());
//                        nextMonthLeave.setPrev_sl(empdata.getSl());
//
//                        empdata.setCl(empdata.getCl() - nextMonthLeave.getDays());
//                        nextMonthLeave.setUp_cl(empdata.getCl());
//                        nextMonthLeave.setUp_el(empdata.getEl());
//                        nextMonthLeave.setUp_sl(empdata.getSl());
//
//                        employeeService.save(empdata);
//                        leaveRequestService.save(nextMonthLeave);
//                    } else {
//                        // Handle single month leave
//                        empdata.setCl(empdata.getCl() - lve.getDays());
//                        lve.setUp_cl(empdata.getCl());
//                        lve.setUp_el(empdata.getEl());
//                        lve.setUp_sl(empdata.getSl());
//                        leaveRequestService.save(lve);
//                    }
//
//                    return "Leave submitted and waiting for approval";
//                } else {
//                    return "Insufficient leaves";
//                }
//            }
//    		else if("el".equalsIgnoreCase(leavetp)) {
//    			if(empdata.getEl() >= lve.getDays()) {
//    				lve.setPrev_cl(empdata.getCl());
//        			lve.setPrev_el(empdata.getEl());
//        			lve.setPrev_sl(empdata.getSl());
//        			empdata.setEl(empdata.getEl()-lve.getDays());
//        			lve.setUp_cl(empdata.getCl());
//        			lve.setUp_el(empdata.getEl());
//        			lve.setUp_sl(empdata.getSl());
//        			employeeService.save(empdata);
//        			leaveRequestService.save(lve);
//            		return "leave submitted and waiting for approval";
//        		}
//    			else {
//        			return "Insufficient leaves";
//        		}
//    		}
//    		else if("sl".equalsIgnoreCase(leavetp)) {
//    			if(empdata.getSl() >= lve.getDays()) {
//    				lve.setPrev_cl(empdata.getCl());
//        			lve.setPrev_el(empdata.getEl());
//        			lve.setPrev_sl(empdata.getSl());
//        			empdata.setSl(empdata.getSl()-lve.getDays());
//        			lve.setUp_cl(empdata.getCl());
//        			lve.setUp_el(empdata.getEl());
//        			lve.setUp_sl(empdata.getSl());
//        			employeeService.save(empdata);
//        			leaveRequestService.save(lve);
//            		return "leave submitted and waiting for approval";
//        		}
//    			else {
//        			return "Insufficient leaves";
//        		}
//    		}
//    		return "choose leave type";
//    	}
//    	else {
//    		return "emp not found";
//    	}
//	}
    
    
    @PostMapping("/insertleave")
    public String createleave(@RequestBody LeaveRequest lve) {
        String leavetp = lve.getLeavetype();
        Optional<Employee> op = employeeService.findById(lve.getEmployeeId());

        if(op.isPresent()) {
            Employee empdata = op.get();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//            LocalDate startDate = LocalDate.parse(lve.getStartdate(), DateTimeFormatter.ofPattern("dd-MM-yy"));
//            LocalDate endDate = LocalDate.parse(lve.getEnddate(), DateTimeFormatter.ofPattern("dd-MM-yy"));
            LocalDate startDate = LocalDate.parse(lve.getStartdate(), formatter);
            LocalDate endDate = LocalDate.parse(lve.getEnddate(), formatter);
            int daysInStartMonth = startDate.lengthOfMonth() - startDate.getDayOfMonth() + 1;

            if ("cl".equalsIgnoreCase(leavetp)) {
                if (empdata.getCl() >= lve.getDays()) {
                    lve.setPrev_cl(empdata.getCl());
                    lve.setPrev_el(empdata.getEl());
                    lve.setPrev_sl(empdata.getSl());

                    if (!startDate.getMonth().equals(endDate.getMonth())) {
                        // Handle first part of leave in the start month
                        empdata.setCl(empdata.getCl() - daysInStartMonth);
                        lve.setUp_cl(empdata.getCl());
                        lve.setUp_el(empdata.getEl());
                        lve.setUp_sl(empdata.getSl());

                        // Adjust end date to the last day of the start month
                        LocalDate adjustedEndDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
                        lve.setEnddate(adjustedEndDate.format(DateTimeFormatter.ofPattern("dd-MM-yy")));
                        leaveRequestService.save(lve);

                        // Handle second part of leave in the next month
                        LeaveRequest nextMonthLeave = new LeaveRequest();
                        nextMonthLeave.setEmployeeId(lve.getEmployeeId());
                        nextMonthLeave.setLeavetype(lve.getLeavetype());
                        nextMonthLeave.setStartdate("01-" + endDate.format(DateTimeFormatter.ofPattern("MM-yy")));
                        nextMonthLeave.setEnddate(endDate.format(DateTimeFormatter.ofPattern("dd-MM-yy")));
                        nextMonthLeave.setDays(lve.getDays() - daysInStartMonth);
                        nextMonthLeave.setPrev_cl(empdata.getCl());
                        nextMonthLeave.setPrev_el(empdata.getEl());
                        nextMonthLeave.setPrev_sl(empdata.getSl());
                        nextMonthLeave.setReason(lve.getReason());  // Carry over the reason

                        empdata.setCl(empdata.getCl() - nextMonthLeave.getDays());
                        nextMonthLeave.setUp_cl(empdata.getCl());
                        nextMonthLeave.setUp_el(empdata.getEl());
                        nextMonthLeave.setUp_sl(empdata.getSl());

                        empdata.setCl(nextMonthLeave.getUp_cl());
                        employeeService.save(empdata);
                        leaveRequestService.save(nextMonthLeave);

                        return "Leave submitted";
                    } 
                    else {
                        // Handle leave within the same month
                        empdata.setCl(empdata.getCl() - lve.getDays());
                        lve.setUp_cl(empdata.getCl());
                        lve.setUp_el(empdata.getEl());
                        lve.setUp_sl(empdata.getSl());
                        leaveRequestService.save(lve);
                        employeeService.save(empdata);
                        return "Leave submitted";
                    }
                } 
                
                else {
                    return "Insufficient leaves";
                }
            }
            // Similar logic for EL and SL can be added here, return string accordingly.
            if ("el".equalsIgnoreCase(leavetp)) {
                if (empdata.getEl() >= lve.getDays()) {
                    lve.setPrev_cl(empdata.getCl());
                    lve.setPrev_el(empdata.getEl());
                    lve.setPrev_sl(empdata.getSl());
                    
                    if (!startDate.getMonth().equals(endDate.getMonth())) {
                        // Handle first part of leave in the start month
                        empdata.setEl(empdata.getEl() - daysInStartMonth);
                        lve.setUp_cl(empdata.getCl());
                        lve.setUp_el(empdata.getEl());
                        lve.setUp_sl(empdata.getSl());

                        // Adjust end date to the last day of the start month
                        LocalDate adjustedEndDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
                        lve.setEnddate(adjustedEndDate.format(DateTimeFormatter.ofPattern("dd-MM-yy")));
                        leaveRequestService.save(lve);

                        // Handle second part of leave in the next month
                        LeaveRequest nextMonthLeave = new LeaveRequest();
                        nextMonthLeave.setEmployeeId(lve.getEmployeeId());
                        nextMonthLeave.setLeavetype(lve.getLeavetype());
                        nextMonthLeave.setStartdate("01-" + endDate.format(DateTimeFormatter.ofPattern("MM-yy")));
                        nextMonthLeave.setEnddate(endDate.format(DateTimeFormatter.ofPattern("dd-MM-yy")));
                        nextMonthLeave.setDays(lve.getDays() - daysInStartMonth);
                        nextMonthLeave.setPrev_cl(empdata.getCl());
                        nextMonthLeave.setPrev_el(empdata.getEl());
                        nextMonthLeave.setPrev_sl(empdata.getSl());
                        nextMonthLeave.setReason(lve.getReason());  // Carry over the reason

                        empdata.setEl(empdata.getEl() - nextMonthLeave.getDays());
                        nextMonthLeave.setUp_cl(empdata.getCl());
                        nextMonthLeave.setUp_el(empdata.getEl());
                        nextMonthLeave.setUp_sl(empdata.getSl());
                        
//                        empdata.setEl(nextMonthLeave.getUp_el());
                        employeeService.save(empdata);
                        leaveRequestService.save(nextMonthLeave);

                        return "Leave submitted";
                    } 
                    else {
                        // Handle leave within the same month
                        empdata.setEl(empdata.getEl() - lve.getDays());
                        lve.setUp_cl(empdata.getCl());
                        lve.setUp_el(empdata.getEl());
                        lve.setUp_sl(empdata.getSl());
                        leaveRequestService.save(lve);
                        employeeService.save(empdata);
                        return "Leave submitted";
                    }
                } 
                
                else {
                    return "Insufficient leaves";
                }
            }
            if ("sl".equalsIgnoreCase(leavetp)) {
                if (empdata.getSl() >= lve.getDays()) {
                    lve.setPrev_cl(empdata.getCl());
                    lve.setPrev_el(empdata.getEl());
                    lve.setPrev_sl(empdata.getSl());

                    if (!startDate.getMonth().equals(endDate.getMonth())) {
                        // Handle first part of leave in the start month
                        empdata.setSl(empdata.getSl() - daysInStartMonth);
                        lve.setUp_cl(empdata.getCl());
                        lve.setUp_el(empdata.getEl());
                        lve.setUp_sl(empdata.getSl());

                        // Adjust end date to the last day of the start month
                        LocalDate adjustedEndDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
                        lve.setEnddate(adjustedEndDate.format(DateTimeFormatter.ofPattern("dd-MM-yy")));
                        leaveRequestService.save(lve);

                        // Handle second part of leave in the next month
                        LeaveRequest nextMonthLeave = new LeaveRequest();
                        nextMonthLeave.setEmployeeId(lve.getEmployeeId());
                        nextMonthLeave.setLeavetype(lve.getLeavetype());
                        nextMonthLeave.setStartdate("01-" + endDate.format(DateTimeFormatter.ofPattern("MM-yy")));
                        nextMonthLeave.setEnddate(endDate.format(DateTimeFormatter.ofPattern("dd-MM-yy")));
                        nextMonthLeave.setDays(lve.getDays() - daysInStartMonth);
                        nextMonthLeave.setPrev_cl(empdata.getCl());
                        nextMonthLeave.setPrev_el(empdata.getEl());
                        nextMonthLeave.setPrev_sl(empdata.getSl());
                        nextMonthLeave.setReason(lve.getReason());  // Carry over the reason

                        empdata.setSl(empdata.getSl() - nextMonthLeave.getDays());
                        nextMonthLeave.setUp_cl(empdata.getCl());
                        nextMonthLeave.setUp_el(empdata.getEl());
                        nextMonthLeave.setUp_sl(empdata.getSl());
                        
                        empdata.setSl(nextMonthLeave.getUp_sl());
                        employeeService.save(empdata);
                        leaveRequestService.save(nextMonthLeave);

                        return "Leave submitted";
                    } 
                    else {
                        // Handle leave within the same month
                        empdata.setSl(empdata.getSl() - lve.getDays());
                        lve.setUp_cl(empdata.getCl());
                        lve.setUp_el(empdata.getEl());
                        lve.setUp_sl(empdata.getSl());
                        leaveRequestService.save(lve);
                        employeeService.save(empdata);
                        return "Leave submitted";
                    }
                } 
                
                else {
                    return "Insufficient leaves";
                }
            }
            return "Leave type not recognized";  // Ensure a return statement for unhandled leave types.
        } else {
            return "Employee not found";
        }
    }


    
    
    
    @GetMapping("/getemp")
    public ResponseEntity<?> getemp(@RequestParam String em){
    	Optional<Employee> pendingdet = employeeService.findById(em);
    	return ResponseEntity.ok(Collections.singletonList(pendingdet.get()));
    }
    
    @GetMapping("/getleave")
    public ResponseEntity<?> getleavedet(@RequestParam String idd){
    	Optional<Employee> pendingdet = employeeService.findById(idd);
    	return ResponseEntity.ok(Collections.singletonList(pendingdet.get()));
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        Optional<Employee> employeeOpt = employeeService.findById(loginRequest.getUserid());
        
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            if (employee.getPassword().equals(loginRequest.getPassword())) {
//                return ResponseEntity.ok(employee);
            	return "Login successful";
            } else {
//                return ResponseEntity.status(401).body("Invalid password");
            	return "Invalid Password";
            }
        } else {
//            return ResponseEntity.status(404).body("Employee not found");
        	return "Employee not found";
        }
    }
}

