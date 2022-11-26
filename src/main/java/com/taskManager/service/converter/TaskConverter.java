package com.taskManager.service.converter;

import com.taskManager.model.entity.Task;
import com.taskManager.service.dto.TaskCreateDto;

public interface TaskConverter {
    Task convertToTask(TaskCreateDto taskDto);
//    TaskCreateDto convertToTaskDto(Task task);
}
