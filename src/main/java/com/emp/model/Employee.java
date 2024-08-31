package com.emp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "employees")
public class Employee {
	
//	@Id
//	String id;
	@Id
	 String empid;
     String username;
     String password;
     double cl;
     double sl;
     double el;
     int totalLeaves;
}
