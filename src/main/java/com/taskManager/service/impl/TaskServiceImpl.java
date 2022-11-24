package com.taskManager.service.impl;

import com.taskManager.model.entity.Employee;
import com.taskManager.model.entity.Task;
import com.taskManager.model.repository.TaskRepository;
import com.taskManager.service.EmployeeService;
import com.taskManager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeService employeeService;

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
        if (workDay == null) {
            date = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        } else {
            date = workDay;
        }
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
        return tasks.stream()
                .filter(task -> smf.format(task.getWorkday()).equals(smf.format(date)))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> filterByExecutor(List<Task> tasks, String function) {
        return tasks.stream()
                .map(Task::getEmployees)
                .flatMap(List::stream)
                .filter(employee -> employee.getName().equalsIgnoreCase(function))
                .map(Employee::getTasks)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public void save(Task task) {
        var employees = new ArrayList<>(task.getEmployees());
        var returnedTask = taskRepository.saveAndFlush(task);
        for (var employee : employees) {
            employee.getTasks().add(returnedTask);
        }
        employeeService.saveAll(employees);
    }
//    public Task mapToTask(TaskDto taskDto) {
//        var task = new Task();
//        task.setId(taskDto.getId());
//        task.setName(taskDto.getName());
//        task.setDescription(taskDto.getDescription());
//        task.setWorkday(taskDto.getWorkday());
//        task.setPriority(taskDto.getPriority());
//        task.setDeadLine(taskDto.getDeadLine());
//        task.setEmployees(taskDto.getEmployees().stream()
//                .map(Employee::getId)
//                .map(employeeService::findById)
//                .collect(Collectors.toList()));
//        return task;
//    }

//    public TaskDto mapToTaskDto(Task task) {
//        var taskDto = new TaskDto();
//        taskDto.setId(task.getId());
//        taskDto.setName(task.getName());
//        taskDto.setDescription(task.getDescription());
//        taskDto.setWorkday(task.getWorkday());
//        taskDto.setPriority(task.getPriority());
//        taskDto.setDeadLine(task.getDeadLine());
//        taskDto.setEmployees(task.getEmployees());
//        return taskDto;
//    }
}
