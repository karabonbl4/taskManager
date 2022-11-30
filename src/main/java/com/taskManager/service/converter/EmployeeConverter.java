package com.taskManager.service.converter;

import com.taskManager.model.entity.Employee;
import com.taskManager.service.dto.EmployeeDto;

public interface EmployeeConverter {
    EmployeeDto convertToEmployeeDto(Employee employee);
    Employee convertToEmployee(EmployeeDto employeeDto);
}
