package com.taskManager.service;

import com.taskManager.model.entity.Task;

import java.util.Date;
import java.util.List;

public interface TaskService {
    List<Task> findByDate(Date date);
    List<Task> findByDepartmentId(long id);
    List<Task> filterByDate(List<Task> tasks, Date date);
    void save(Task task);
}
