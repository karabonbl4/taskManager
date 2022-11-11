package com.taskManager.service;

import com.taskManager.model.dto.DepartmentDto;
import com.taskManager.model.entity.Department;

import java.util.List;

public interface DepartmentService {
    boolean save(Department department);
    List<Department> findByUsername();
    List<DepartmentDto> getDepartmentsDto();
    Department findById(long id);
    DepartmentDto convertDepartmentToDepartmentDto(Department department);
}
