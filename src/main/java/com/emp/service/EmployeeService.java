//package com.emp.service;
//
//import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.emp.model.Employee;
//import com.emp.repository.EmployeeRepository;
//
//@Service
//public class EmployeeService {
//	 @Autowired
//	    private EmployeeRepository employeeRepository;
//
//	    public Optional<Employee> findByUsername(String username) {
//	        return employeeRepository.findByUsername(username);
//	    }
//
//	    public Employee save(Employee employee) {
//	        return employeeRepository.save(employee);
//	    }
//
//	    public Optional<Employee> findById(String id) {
//	        return employeeRepository.findById(id);
//	    }
//
//}

package com.emp.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.emp.model.Employee;
import com.emp.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    public void insert(Employee emp) {
    	employeeRepository.save(emp);
    }

    public Optional<Employee> findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> findById(String employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public Employee updateLeaveBalance(String employeeId, int leaveDecrement) {
        Optional<Employee> employeeOpt = findById(employeeId);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            int newTotalLeaves = employee.getTotalLeaves() - leaveDecrement;
            if (newTotalLeaves >= 0) {
                employee.setTotalLeaves(newTotalLeaves);
                return employeeRepository.save(employee);
            }
        }
        return null;
    }
}

