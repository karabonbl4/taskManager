package com.taskManager.service.converter.impl;

import com.taskManager.model.entity.Employee;
import com.taskManager.model.repository.DepartmentRepository;
import com.taskManager.model.repository.UserRepository;
import com.taskManager.service.converter.EmployeeConverter;
import com.taskManager.service.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeConverterImpl implements EmployeeConverter {

    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    @Override
    public EmployeeDto convertToEmployeeDto(Employee employee) {
        var employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setJobTitle(employee.getName());
        employeeDto.setUsername(employee.getUser().getUsername());
        employeeDto.setEmail(employee.getUser().getEmail());
        employeeDto.setDepartmentId(employee.getDepartment().getId());
        return employeeDto;
    }

    @Override
    public Employee convertToEmployee(EmployeeDto employeeDto) {
        var user = userRepository.findByUsername(employeeDto.getUsername());
        var employee = new Employee();
        if (employeeDto.getId()!=null){
            employee.setId(employeeDto.getId());
        }
        employee.setName(employeeDto.getJobTitle());
        employee.setUser(user);
        employee.setDepartment(departmentRepository.getReferenceById(employeeDto.getDepartmentId()));
        return employee;
    }
}
