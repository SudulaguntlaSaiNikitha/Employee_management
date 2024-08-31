//package com.emp.model;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import lombok.Data;
//
//@Data
//@Document(collection = "leaveRequests")
//public class LeaveRequest {
//	
//	@Id
//     String id;
//     String employeeId; // ID of the employee requesting leave
//     String username; // Username of the employee
//     String date; // Date of the leave
//     String reason; // Reason for the leave
//     boolean approved; // Default to false
//}

package com.emp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "leaveRequests")
public class LeaveRequest {

//	@Id
//	 String id;
	
     String employeeId; // ID of the employee requesting leave
     
   
     String startdate;
     String enddate;// Date of the leave
     double days;
     String reason; // Reason for the leave
     String leavetype;
//     String approved = "pending"; 
     double prev_cl=0;
     double prev_sl=0;
     double prev_el=0;
     double up_cl=0;
     double up_sl=0;
     double up_el=0;// Default to false
}

