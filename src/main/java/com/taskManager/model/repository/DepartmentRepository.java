package com.taskManager.model.repository;

import com.taskManager.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findByEmployees_User_Username(String username);
    Department findById(long id);
}
