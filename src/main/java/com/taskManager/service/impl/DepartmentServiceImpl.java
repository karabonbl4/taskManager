package com.taskManager.service.impl;

import com.taskManager.model.entity.Department;
import com.taskManager.model.entity.User;
import com.taskManager.model.repository.DepartmentRepository;
import com.taskManager.model.repository.UserRepository;
import com.taskManager.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    @Override
    public List<Department> findByUser(User user) {
        return departmentRepository.findByUser(user);
    }

    @Override
    public void save(Department department) {
        departmentRepository.save(department);
    }
}
