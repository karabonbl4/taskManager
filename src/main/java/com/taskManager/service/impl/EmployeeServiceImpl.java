package com.taskManager.service.impl;

import com.taskManager.model.dto.EmployeeDto;
import com.taskManager.model.entity.Department;
import com.taskManager.model.entity.Employee;
import com.taskManager.model.entity.User;
import com.taskManager.model.repository.EmployeeRepository;
import com.taskManager.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public Employee findByUserAndDepartment(User user, Department department) {
        return employeeRepository.findByUserAndDepartment(user, department);
    }

    @Override
    public List<Employee> findByUser(User user) {
        return employeeRepository.findByUser(user);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeDto convertToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new com.taskManager.model.dto.EmployeeDto();
        employeeDto.setJobTitle(employee.getName());
        employeeDto.setUsername(employee.getUser().getUsername());
        return employeeDto;
    }
}
