package com.taskManager.service.mapper.impl;

import com.taskManager.model.entity.Task;
import com.taskManager.service.EmployeeService;
import com.taskManager.service.dto.TaskCreateDto;
import com.taskManager.service.mapper.DateConverter;
import com.taskManager.service.mapper.TaskConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskConverterImpl implements TaskConverter {
    private final EmployeeService employeeService;
    private final DateConverter dateConverter;
    @Override
    public Task convertToTask(TaskCreateDto taskCreateDto) {
        Task task = new Task();
        task.setName(taskCreateDto.getName());
        task.setDescription(taskCreateDto.getDescription());
        task.setWorkday(taskCreateDto.getWorkday());
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        var stringDeadline = dateFormat.format(taskCreateDto.getDeadlineDate()).replace("00:00:00", taskCreateDto.getDeadlineTime().concat(":00"));
        var deadLine = dateConverter.convertToDate(stringDeadline);
        task.setDeadLine(deadLine);
        task.setPriority(taskCreateDto.getPriority());
        List<Long> employeesId= new ArrayList<>();
        var executors = Arrays.stream(taskCreateDto.getExecutors().split(","))
                .map(Long::parseLong)
                .map(employeeService::findById)
                .collect(Collectors.toList());
        task.setEmployees(executors);
        return task;
    }

    @Override
    public TaskCreateDto convertToTaskDto(Task task) {
        return null;
    }
}
