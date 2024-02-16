package com.redbix.controller;

import com.redbix.entity.Employee;
import com.redbix.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEmployeeById() {
        long id = 1L;
        Employee employee = new Employee();
        employee.setId(id);
        when(employeeService.getEmployeeById(id)).thenReturn(Optional.of(employee));

        ResponseEntity<Employee> response = employeeController.getEmployeeById(id);
        assertEquals(id, response.getBody().getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testAddEmployee() {
        Employee employee = new Employee();
        when(employeeService.addEmployee(employee)).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.addEmployee(employee);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testUpdateEmployee() {
        long id = 1L;
        Employee employee = new Employee();
        when(employeeService.updateEmployee(eq(id), any(Employee.class))).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.updateEmployee(id, employee);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteEmployee() {
        long id = 1L;
        ResponseEntity<Void> response = employeeController.deleteEmployee(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
