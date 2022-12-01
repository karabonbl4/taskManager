package com.taskManager.service.impl;

import com.taskManager.model.entity.Customer;
import com.taskManager.model.entity.Department;
import com.taskManager.model.entity.Employee;
import com.taskManager.model.entity.Material;
import com.taskManager.model.repository.DepartmentRepository;
import com.taskManager.model.repository.EmployeeRepository;
import com.taskManager.service.dto.CustomerDto;
import com.taskManager.service.dto.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
    private static final Long DEPARTMENT_ID = 11L;
    private static final Long ID = 1L;
    private static final String NAME = "worker";

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private DepartmentRepository departmentRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void save() {
        final Employee employee = mock(Employee.class);

        employeeService.save(employee);

        verify(employeeRepository).saveAndFlush(employee);
    }

    @Test
    void saveAll() {
        final List<Employee> employees = mock(List.class);

        employeeService.saveAll(employees);

        verify(employeeRepository).saveAllAndFlush(employees);
    }

    @Test
    void findById() {
        final Employee employee = mock(Employee.class);
        when(employeeRepository.getReferenceById(ID)).thenReturn(employee);

        final Employee actual = employeeService.findById(ID);

        assertNotNull(actual);
        assertEquals(employee, actual);
        verify(employeeRepository).getReferenceById(ID);
    }

    @Test
    void update() {
        final Employee employee = mock(Employee.class);
        final Department department = mock(Department.class);
        final EmployeeDto employeeDto = mock(EmployeeDto.class);
        final List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        when(employeeDto.getDepartmentId()).thenReturn(DEPARTMENT_ID);
        when(departmentRepository.getReferenceById(DEPARTMENT_ID)).thenReturn(department);
        when(department.getEmployees()).thenReturn(employees);
        when(employeeDto.getJobTitle()).thenReturn(NAME);
        when(employee.getName()).thenReturn(NAME);

        boolean actualFalse = employeeService.update(employeeDto);

        assertFalse(actualFalse);

        when(employeeDto.getDepartmentId()).thenReturn(DEPARTMENT_ID);
        when(departmentRepository.getReferenceById(DEPARTMENT_ID)).thenReturn(department);
        when(department.getEmployees()).thenReturn(employees);
        when(employeeDto.getJobTitle()).thenReturn("new job title");
        when(employee.getName()).thenReturn(NAME);
        when(employeeDto.getId()).thenReturn(ID);
        when(employeeRepository.getReferenceById(ID)).thenReturn(employee);

        boolean actualTrue = employeeService.update(employeeDto);

        assertTrue(actualTrue);
        verify(employeeRepository).saveAndFlush(employee);
    }

    @Test
    void delete() {
        final Employee employee = mock(Employee.class);
        final EmployeeDto employeeDto = mock(EmployeeDto.class);
        when(employeeRepository.getReferenceById(ID)).thenReturn(employee);
        when(employeeDto.getId()).thenReturn(ID);
        employeeService.delete(employeeDto);

        verify(employeeRepository).saveAndFlush(employee);
    }
}