package com.taskManager.service;

import com.taskManager.model.entity.Department;
import com.taskManager.model.entity.Employee;
import com.taskManager.model.entity.User;

import java.util.List;

public interface EmployeeService {
    Employee findByUserAndDepartment(User user, Department department);
    List<Employee> findByUser(User user);
    void save(Employee employee);
    com.taskManager.model.dto.EmployeeDto convertToEmployeeDto(Employee employee);

}
