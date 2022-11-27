package com.taskManager.service;


import com.taskManager.model.entity.Task;
import com.taskManager.service.dto.TaskDto;
import com.taskManager.service.dto.WorkDayWithDepartmentIdDto;

import java.util.Date;
import java.util.List;

public interface TaskService {
    List<Task> findByDate(Date date);
    List<Task> findByDepartmentId(long id);
    List<Task> filterByDate(List<Task> tasks, Date date);
    List<Task> filterByExecutorAndDate(List<Task> tasks, String function, Date date);
    void save(Task task);
    boolean update(TaskDto taskDto);
    List<Task> getFilteredTask(WorkDayWithDepartmentIdDto workday);


}
