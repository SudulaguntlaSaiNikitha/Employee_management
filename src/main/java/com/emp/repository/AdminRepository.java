package com.emp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emp.model.Admin;

public interface AdminRepository extends MongoRepository<Admin, String> {

}
