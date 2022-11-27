package com.taskManager.service;

import com.taskManager.model.entity.Department;
import com.taskManager.model.entity.Employee;
import com.taskManager.model.entity.User;
import com.taskManager.service.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    Employee findByUserAndDepartment(User user, Department department);
    List<Employee> findByUser(User user);
    void save(Employee employee);
    void saveAll(List<Employee> employees);
    Employee findById(Long id);
    EmployeeDto convertToEmployeeDto(Employee employee);
    Employee convertToEmployee (EmployeeDto employeeDto);
    boolean update(EmployeeDto editEmployee);
    void delete(EmployeeDto employeeDto);


}
