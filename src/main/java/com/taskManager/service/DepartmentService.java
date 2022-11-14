package com.taskManager.service;

import com.taskManager.model.entity.Department;
import com.taskManager.service.dto.*;

import java.util.List;

public interface DepartmentService {
    boolean save(Department department);
    List<Department> findByUsername();
    List<DepartmentDto> getDepartmentsDto();
    DepartmentDto findById(long id);
    DepartmentDto convertToDepartmentDto(Department department);
    Department convertToDepartment(DepartmentDto departmentDto);
    List<EmployeeDto> getDepartmentEmployees(Long departmentId);
    List<MaterialDto> getDepartmentMaterials(Long departmentId);
    List<ProviderDto> getDepartmentProviders(Long departmentId);
    List<CustomerDto> getDepartmentCustomers(Long departmentId);
    Department getById(Long id);
}
