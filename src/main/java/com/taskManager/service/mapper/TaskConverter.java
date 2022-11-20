package com.taskManager.service.mapper;

import com.taskManager.model.entity.Task;
import com.taskManager.service.dto.TaskCreateDto;

import java.text.ParseException;

public interface TaskConverter {
    Task convertToTask(TaskCreateDto taskDto);
    TaskCreateDto convertToTaskDto(Task task);
}
