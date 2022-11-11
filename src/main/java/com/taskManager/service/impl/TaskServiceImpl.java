package com.taskManager.service.impl;

import com.taskManager.model.entity.Task;
import com.taskManager.model.repository.TaskRepository;
import com.taskManager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public List<Task> findByDate(Date date) {
        return taskRepository.findByWorkday(date);
    }

    @Override
    public List<Task> findByDepartmentId(long id) {
        return taskRepository.findByEmployees_Department_Id(id);
    }

    @Override
    public List<Task> filterByDate(List<Task> tasks, Date workDay) {
        var filteredTasks = tasks.stream()
                .filter(task -> task.getWorkday().equals(workDay))
                .collect(Collectors.toList());
        return filteredTasks;
    }
}
