package com.taskManager.service.impl;


import com.taskManager.model.entity.Department;
import com.taskManager.model.entity.Employee;
import com.taskManager.model.repository.DepartmentRepository;
import com.taskManager.service.*;
import com.taskManager.service.dto.*;
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
    private final CustomerService customerService;
    private final MaterialService materialService;
    private final ProviderService providerService;

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
                .map(this::convertToDepartmentDto)
                .collect(Collectors.toList());
    }

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
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());
        department.setLocation(departmentDto.getLocation());
        return department;
    }

    @Override
    public List<EmployeeDto> getDepartmentEmployees(Long departmentId) {
        return departmentRepository.findById(departmentId).getEmployees().stream()
                .map(employeeService::convertToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MaterialDto> getDepartmentMaterials(Long departmentId) {
        return departmentRepository.findById(departmentId).getMaterials().stream()
                .map(materialService::convertToMaterialDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProviderDto> getDepartmentProviders(Long departmentId) {
        return departmentRepository.findById(departmentId).getProviders().stream()
                .map(providerService::convertToProviderDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> getDepartmentCustomers(Long departmentId) {
        return departmentRepository.findById(departmentId).getCustomers().stream()
                .map(customerService::convertToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public Department getById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public DepartmentDto findById(long id) {
        return convertToDepartmentDto(departmentRepository.findById(id));
    }

}