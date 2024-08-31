package com.emp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "admins")
public class Admin {
//    @Id
//    String id;
//    String username;
	int el;
	int sl;
	int cl;
//    String approve;
    // Add other relevant fields if necessary

//	public String getApprove() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
