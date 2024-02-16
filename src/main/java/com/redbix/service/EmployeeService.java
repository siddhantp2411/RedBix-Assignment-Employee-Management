package com.redbix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.redbix.entity.Employee;

public interface EmployeeService {


	Optional<Employee> getEmployeeById(Long id);

	Employee addEmployee(Employee employee);

	Employee updateEmployee(Long id, Employee employeeDetails);

	void deleteEmployee(Long id);

	Page<Employee> getAllEmployees(int page, int size, String sortBy);

	List<Employee> getAllEmployees();

}
