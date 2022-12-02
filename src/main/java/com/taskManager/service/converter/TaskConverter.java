package com.taskManager.service.converter;

import com.taskManager.model.entity.Task;
import com.taskManager.service.dto.TaskDto;

public interface TaskConverter {
    Task convertToTask(TaskDto taskDto);
    TaskDto convertToTaskDto(Task task);
}
