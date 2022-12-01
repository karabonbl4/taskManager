package com.taskManager.service.converter;

import com.taskManager.model.entity.Department;
import com.taskManager.service.dto.DepartmentDto;

public interface DepartmentConverter {
    DepartmentDto convertToDepartmentDto(Department department);
    Department convertToDepartment(DepartmentDto departmentDto);
}
