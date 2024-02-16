package com.redbix.service.impl;

import com.redbix.entity.Employee;
import com.redbix.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "John", "Doe", "john.doe@example.com", "Developer"));
        employees.add(new Employee(2L, "Jane", "Doe", "jane.doe@example.com", "Manager"));
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> found = employeeService.getAllEmployees();
        assertEquals(2, found.size());
    }

    @Test
    void testGetEmployeeById() {
        long id = 1L;
        Employee employee = new Employee();
        employee.setId(id);
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        Optional<Employee> found = employeeService.getEmployeeById(id);
        assertEquals(id, found.get().getId());
    }

    @Test
    void testAddEmployee() {
        Employee employee = new Employee(1L, "John", "Doe", "john.doe@example.com", "Developer");
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee savedEmployee = employeeService.addEmployee(employee);
        assertEquals(employee.getId(), savedEmployee.getId());
    }

    @Test
    void testUpdateEmployee() {
        long id = 1L;
        Employee existingEmployee = new Employee(id, "John", "Doe", "john.doe@example.com", "Developer");
        Employee updatedEmployee = new Employee(id, "John", "Doe", "john.doe@example.com", "Manager");
        when(employeeRepository.findById(id)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(existingEmployee)).thenReturn(updatedEmployee);

        Employee result = employeeService.updateEmployee(id, updatedEmployee);
        assertEquals("Manager", result.getDesignation());
    }

    @Test
    void testDeleteEmployee() {
        long id = 1L;
        employeeService.deleteEmployee(id);
        verify(employeeRepository, times(1)).deleteById(id);
    }
}
