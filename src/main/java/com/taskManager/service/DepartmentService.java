package com.taskManager.service;

import com.taskManager.model.entity.Department;
import com.taskManager.service.dto.*;

import java.util.List;

public interface DepartmentService {
    boolean save(DepartmentDto departmentDto);

    List<Department> findByAuthUsername();

    List<DepartmentDto> getDepartmentsDto();

    DepartmentDto findById(long id);

    List<EmployeeDto> getDepartmentEmployees(Long departmentId);

    List<MaterialDto> getDepartmentMaterials(Long departmentId);

    List<ProviderDto> getDepartmentProviders(Long departmentId);

    List<CustomerDto> getDepartmentCustomers(Long departmentId);

    boolean update(DepartmentDto departmentDto);

    WorkDayWithDepartmentIdDto getWorkdayToday();

    boolean containEmployee(EmployeeDto employeeDto);
}
