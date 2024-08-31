package com.emp.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class User {
	@Id
	String userid;
    String password;
}
