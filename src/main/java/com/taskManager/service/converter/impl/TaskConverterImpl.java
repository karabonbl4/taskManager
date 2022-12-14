package com.taskManager.service.converter.impl;

import com.taskManager.model.entity.Employee;
import com.taskManager.model.entity.Material;
import com.taskManager.model.entity.Task;
import com.taskManager.model.entity.TempMaterial;
import com.taskManager.service.EmployeeService;
import com.taskManager.service.MaterialService;
import com.taskManager.service.converter.DateConverter;
import com.taskManager.service.converter.MaterialConverter;
import com.taskManager.service.converter.TaskConverter;
import com.taskManager.service.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class TaskConverterImpl implements TaskConverter {
    private final EmployeeService employeeService;
    private final DateConverter dateConverter;
    private final MaterialConverter materialConverter;

    @Override
    public Task convertToTask(TaskDto taskDto) {
        var task = new Task();
        if (taskDto.getId() != null) {
            task.setId(taskDto.getId());
        }
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setWorkday(taskDto.getWorkday());
        var dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        var stringDeadline = dateFormat.format(taskDto.getDeadlineDate()).replace("00:00:00", taskDto.getDeadlineTime().concat(":00"));
        var deadLine = dateConverter.convertToDate(stringDeadline);
        task.setDeadLine(deadLine);
        task.setPriority(taskDto.getPriority());
        task.setCondition(taskDto.getCondition());
        var executors = Arrays.stream(taskDto.getExecutors().split(","))
                .map(Long::parseLong)
                .map(employeeService::findById)
                .collect(Collectors.toList());
        task.setEmployees(executors);
        if(taskDto.getTempMaterials()!=""&&taskDto.getTempMaterials()!=null) {
            var tempMaterials = materialConverter.convertToTempMaterial(taskDto.getTempMaterials());
            task.setTempMaterials(tempMaterials);
        }
        return task;
    }

    @Override
    public TaskDto convertToTaskDto(Task task) {
        var taskDto = new TaskDto();
        if (task.getId() != null) {
            taskDto.setId(task.getId());
        }
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setPriority(task.getPriority());
        taskDto.setCondition(task.getCondition());
        taskDto.setWorkday(task.getWorkday());
        var dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date deadlineDate;
        try {
            deadlineDate = dateFormat.parse(task.getDeadLine().toString());
        } catch (ParseException e) {
            System.err.print(e);
            deadlineDate = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        }
        taskDto.setDeadlineDate(deadlineDate);
        var timeFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        var deadlineTime = timeFormat.format(task.getDeadLine());
        taskDto.setDeadlineTime(deadlineTime);
        var executors = task.getEmployees().stream()
                .map(Employee::getId)
                .toString()
                .concat(",");
        taskDto.setExecutors(executors);
        if(task.getTempMaterials()!=null){
            taskDto.setTempMaterials(task.getTempMaterials().toString());
        }
        return taskDto;
    }
}
