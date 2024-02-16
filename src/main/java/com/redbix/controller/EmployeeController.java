package com.redbix.controller;

import com.redbix.entity.Employee;

import com.redbix.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Endpoint to retrieve all employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // Endpoint to retrieve an employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.of(employeeService.getEmployeeById(id));
      }

    // Endpoint to add a new employee
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee)   {
        Employee newEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
       }

    // Endpoint to update an existing employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
      }

    // Endpoint to delete an employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
          }   
    
    @GetMapping("/employees")
    public Page<Employee> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy ) {

        return employeeService.getAllEmployees(page, size, sortBy);
          }
        }
