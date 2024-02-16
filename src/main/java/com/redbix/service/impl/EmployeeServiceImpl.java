package com.redbix.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.redbix.entity.Employee;
import com.redbix.repository.EmployeeRepository;
import com.redbix.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	 private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                    existingEmployee.setFirstName(employeeDetails.getFirstName());
                    existingEmployee.setLastName(employeeDetails.getLastName());
                    existingEmployee.setEmail(employeeDetails.getEmail());
                    existingEmployee.setDesignation(employeeDetails.getDesignation());
                    return employeeRepository.save(existingEmployee);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found with id : " + id));
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
    
    public Employee addEmployee1(Employee employee) {
        try {
            // Business logic to add employee
            return employeeRepository.save(employee);
        } catch (Exception e) {
            logger.error("Error occurred while adding employee: {}", e.getMessage());
            throw e; // Rethrow the exception or handle it as appropriate
        }
    }

    @Override
    public Page<Employee> getAllEmployees(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return employeeRepository.findAll(pageable);
    }
}
