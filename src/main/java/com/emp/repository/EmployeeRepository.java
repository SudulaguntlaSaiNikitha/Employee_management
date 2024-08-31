package com.emp.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.emp.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
	Optional<Employee> findByUsername(String username);
	Optional<Employee> findById(String employeeId);
}
