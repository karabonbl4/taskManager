package com.taskManager.service.converter;

import com.taskManager.model.entity.Task;
import com.taskManager.service.dto.TaskCreateDto;
import com.taskManager.service.dto.TaskDto;

public interface TaskConverter {
    Task convertToTask(TaskCreateDto taskCreateDto);
    Task convertToTask(TaskDto taskDto);
    TaskDto convertToTaskDto(Task task);
}
