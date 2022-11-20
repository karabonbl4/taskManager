package com.taskManager.model.repository;

import com.taskManager.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByWorkday(Date workday);
    List<Task> findByEmployees_Department_Id(long id);
}
