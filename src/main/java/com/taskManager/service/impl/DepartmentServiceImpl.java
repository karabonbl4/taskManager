package com.taskManager.service.impl;

import com.taskManager.model.dto.DepartmentDto;
import com.taskManager.model.entity.Department;
import com.taskManager.model.entity.Employee;
import com.taskManager.model.repository.DepartmentRepository;
import com.taskManager.service.DepartmentService;
import com.taskManager.service.EmployeeService;
import com.taskManager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final UserService userService;
    private final EmployeeService employeeService;

    @Override
    public boolean save(Department department) {
        if (findByUsername().stream()
                .anyMatch(dept -> dept.getName().equalsIgnoreCase(department.getName())) || department.getName().equalsIgnoreCase("")) {
            return false;
        }
        employeeService.save(new Employee(userService.getAuthUser(), "manager", departmentRepository.save(department)));
        return true;
    }

    @Override
    public List<Department> findByUsername() {
        return departmentRepository.findByEmployees_User_Username(userService.getAuthUser().getUsername());
    }

    @Override
    public List<DepartmentDto> getDepartmentsDto() {
        return findByUsername().stream()
                .map(this::convertDepartmentToDepartmentDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto convertDepartmentToDepartmentDto(Department department) {
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
    public Department findById(long id) {
        return departmentRepository.findById(id);
    }
}
