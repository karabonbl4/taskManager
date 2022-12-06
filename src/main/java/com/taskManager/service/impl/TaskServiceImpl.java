package com.taskManager.service.impl;

import com.taskManager.model.entity.Employee;
import com.taskManager.model.entity.Task;
import com.taskManager.model.entity.TempMaterial;
import com.taskManager.model.repository.TaskRepository;
import com.taskManager.service.*;
import com.taskManager.service.converter.DateConverter;
import com.taskManager.service.converter.TaskConverter;
import com.taskManager.service.dto.PeriodDto;
import com.taskManager.service.dto.TaskDto;
import com.taskManager.service.dto.WorkDayWithDepartmentIdDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeService employeeService;
    private final TaskConverter taskConverter;
    private final DepartmentService departmentService;
    private final DateConverter dateConverter;
    private final TempMaterialService tempMaterialService;
    private final MaterialService materialService;

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
        var date = Objects.requireNonNullElseGet(workDay, () -> Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
        return tasks.stream()
                .filter(task -> smf.format(task.getWorkday()).equals(smf.format(date)))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> filterByExecutorAndDate(List<Task> tasks, String function, Date workday) {
        var date = Objects.requireNonNullElseGet(workday, () -> Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
         var filteredTasks = tasks.stream()
                .map(task-> task.getEmployees())
                .flatMap(List::stream)
                .filter(employee -> employee.getName().equalsIgnoreCase(function))
                .map(employee-> employee.getTasks())
                .flatMap(List::stream)
                .distinct()
                .filter(task -> smf.format(task.getWorkday()).equals(smf.format(date)))
                .collect(Collectors.toList());
        return filteredTasks;
    }

    @Override
    @Transactional
    public void save(Task task) {
        var employees = new ArrayList<>(task.getEmployees());
        task.setCondition("in_process");
        List<TempMaterial> tempMaterials = null;
        if(task.getTempMaterials()!=null) {
            tempMaterials = task.getTempMaterials();
        }
        var returnedTask = taskRepository.saveAndFlush(task);
        for (var employee : employees) {
            employee.getTasks().add(returnedTask);
        }
        if(tempMaterials!=null){
            for (var tempMaterial:tempMaterials){
                tempMaterial.setTask(returnedTask);
            }
            tempMaterialService.saveAll(tempMaterials);
        }
        employeeService.saveAll(employees);
    }

    @Override
    public boolean update(TaskDto taskDto) {
        var task = taskRepository.getReferenceById(taskDto.getId());
        if (taskDto.getExecutors()==null){
            taskDto.setExecutors(task.getEmployees().stream()
                    .map(Employee::getId)
                    .toString()
                    .concat(","));
        }
        if(taskConverter.convertToTask(taskDto).equals(task)){
            return false;
        }
        var oldEmployees = new ArrayList<>(task.getEmployees());
        var returnedTask = taskRepository.saveAndFlush(taskConverter.convertToTask(taskDto));
        var newEmployees = new ArrayList<>(returnedTask.getEmployees());
        for (var employee : oldEmployees) {
            employee.getTasks().remove(returnedTask);
        }
        for (var employee:newEmployees) {
            employee.getTasks().add(returnedTask);
        }
        employeeService.saveAll(oldEmployees);
        employeeService.saveAll(newEmployees);
        return true;
    }

    @Override
    public List<Task> getByPeriod(PeriodDto periodDto) {
        return findByDepartmentId(periodDto.getDepartmentId()).stream()
                .filter(task->task.getWorkday().after(periodDto.getFromDate()))
                .filter(task -> task.getWorkday().before(periodDto.getToDate()))
                .distinct()
                .sorted(Comparator.comparing(Task::getWorkday))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getByLastWeek(Long departmentId) {
        var today = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        var localToday = dateConverter.convertDateToLocal(today);
        var localLastWeekDay = localToday.minusWeeks(1);
        var lastWeekDay = dateConverter.convertLocalToDate(localLastWeekDay);
        return findByDepartmentId(departmentId).stream()
                .filter(task->task.getWorkday().after(lastWeekDay))
                .filter(task -> task.getWorkday().before(today))
                .distinct()
                .sorted(Comparator.comparing(Task::getWorkday))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getFilteredTask(WorkDayWithDepartmentIdDto workDayWithDepartmentIdDto) {
        var department = departmentService.findById(workDayWithDepartmentIdDto.getDepartmentId());
        var filteredTasks = filterByDate(findByDepartmentId(department.getId()), workDayWithDepartmentIdDto.getDate());
        var doubleFilteredTasks = filterByExecutorAndDate(findByDepartmentId(department.getId()), department.getAuthUserFunction(), workDayWithDepartmentIdDto.getDate());
        if (!department.getAuthUserFunction().equalsIgnoreCase("manager")){
            for (var task:doubleFilteredTasks) {
                if(task.getDeadLine().toInstant().isBefore(Instant.now()) && !task.getCondition().equals("done")){
                    fail(task);
                }
            }
            return doubleFilteredTasks;
        } else {
            for (var task:filteredTasks) {
                if(task.getDeadLine().toInstant().isBefore(Instant.now()) && !task.getCondition().equals("done")){
                    fail(task);
                }
            }
            return filteredTasks;}
    }

    @Override
    public void execute(TaskDto taskDto) {
        var task = taskRepository.getReferenceById(taskDto.getId());
        task.setCondition("confirmed");
        taskRepository.saveAndFlush(task);
    }

    @Override
    @Transactional
    public void confirm(TaskDto taskDto) {
        var task = taskRepository.getReferenceById(taskDto.getId());
        task.setCondition("done");
        if (task.getTempMaterials() != null){
            for (var tempMaterial:task.getTempMaterials()) {
                var material = materialService.findById(tempMaterial.getMaterialId());
                material.setValue(material.getValue() - tempMaterial.getValue());
                materialService.save(material);
            }
        }
        taskRepository.saveAndFlush(task);
    }

    @Override
    public void toWork(TaskDto taskDto) {
        var task = taskRepository.getReferenceById(taskDto.getId());
        task.setCondition("in_process");
        taskRepository.saveAndFlush(task);
    }

    @Override
    public void fail(Task task) {
        task.setCondition("failed");
        taskRepository.saveAndFlush(task);
    }
}
