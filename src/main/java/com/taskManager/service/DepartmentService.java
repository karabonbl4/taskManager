package com.taskManager.service;

import com.taskManager.model.entity.Department;
import com.taskManager.model.entity.User;

import java.util.List;

public interface DepartmentService {
    List<Department> findByUser(User user);
    void save(Department department);
}
