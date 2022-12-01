package com.taskManager.service.converter.impl;

import com.taskManager.model.entity.Department;
import com.taskManager.model.entity.Employee;
import com.taskManager.service.UserService;
import com.taskManager.service.converter.DepartmentConverter;
import com.taskManager.service.dto.DepartmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentConverterImpl implements DepartmentConverter {
    private final UserService userService;
    @Override
    public DepartmentDto convertToDepartmentDto(Department department) {
        var departmentDto = new DepartmentDto();
        var employees = department.getEmployees();
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase("manager")) {
                departmentDto.setManager(employee.getUser().getUsername());
            }
            if (employee.getUser().getUsername().equals(userService.getAuthUser().getUsername())) {
                departmentDto.setAuthUserFunction(employee.getName());
            }
        }
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        departmentDto.setLocation(department.getLocation());
        return departmentDto;
    }

    @Override
    public Department convertToDepartment(DepartmentDto departmentDto) {
        var department = new Department();
        if(departmentDto.getId()!=null){
            department.setId(departmentDto.getId());
        }
        department.setName(departmentDto.getName());
        department.setLocation(departmentDto.getLocation());
        return department;
    }
}
