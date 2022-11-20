package com.taskManager.service.impl;

import com.taskManager.model.entity.Task;
import com.taskManager.model.repository.EmployeeRepository;
import com.taskManager.model.repository.TaskRepository;
import com.taskManager.service.EmployeeService;
import com.taskManager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
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
        Date date;
        if (workDay==null){
            date = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        } else {
            date = workDay;
        }
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
        return tasks.stream()
                .filter(task -> smf.format(task.getWorkday()).equals(smf.format(date)))
                .collect(Collectors.toList());
    }

    @Override
    public void save(Task task) {
        var employees = task.getEmployees();
        for (var employee:employees) {
            employee.getTasks().add(taskRepository.saveAndFlush(task));
            employeeRepository.saveAndFlush(employee);
        }
    }
}
