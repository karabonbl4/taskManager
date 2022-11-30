package com.taskManager.service;

import com.taskManager.model.entity.Employee;
import com.taskManager.service.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    void save(Employee employee);

    void saveAll(List<Employee> employees);

    Employee findById(Long id);

    boolean update(EmployeeDto editEmployee);

    void delete(EmployeeDto employeeDto);


}
