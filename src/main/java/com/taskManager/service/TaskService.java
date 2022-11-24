package com.taskManager.service;


import com.taskManager.model.entity.Task;
import com.taskManager.service.dto.TaskDto;

import java.util.Date;
import java.util.List;

public interface TaskService {
    List<Task> findByDate(Date date);
    List<Task> findByDepartmentId(long id);
    List<Task> filterByDate(List<Task> tasks, Date date);
    List<Task> filterByExecutor(List<Task> tasks, String function);
    void save(Task task);
//    Task mapToTask(TaskDto taskDto);
//    TaskDto mapToTaskDto(Task task);
}
