package com.taskManager.service.impl;

import com.taskManager.model.entity.Task;
import com.taskManager.model.entity.TempMaterial;
import com.taskManager.model.enums.State;
import com.taskManager.model.repository.TaskRepository;
import com.taskManager.service.*;
import com.taskManager.service.converter.DateConverter;
import com.taskManager.service.converter.TaskConverter;
import com.taskManager.service.dto.PeriodDto;
import com.taskManager.service.dto.TaskDto;
import com.taskManager.service.dto.WorkDayWithDepartmentIdDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

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
    @Value("${app.sizePage}")
    private Integer elementOnPage;

    @Override
    public List<Task> findByDate(Date date) {
        return taskRepository.findByWorkday(date);
    }

    @Override
    public List<Task> findByDepartmentId(long id) {
        return taskRepository.findByEmployees_Department_Id(id);
    }

    @Override
    public List<Task> filterByDate(WorkDayWithDepartmentIdDto workday) {
        var date = Objects.requireNonNullElseGet(workday.getDate(), () -> Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        var pageForDB = workday.getPage() - 1;
        var paging = PageRequest.of(pageForDB, elementOnPage, Sort.by("deadLine"));
        return taskRepository.findDistinctByWorkdayAndEmployees_Department_Id(date, workday.getDepartmentId(), paging);
    }

    @Override
    public List<Task> filterByExecutorAndDate(WorkDayWithDepartmentIdDto workday) {
        var date = Objects.requireNonNullElseGet(workday.getDate(), () -> Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        var department = departmentService.findById(workday.getDepartmentId());
        if (workday.getPage()==null){
            workday.setPage(1);
        }
        var pageForDB = workday.getPage() - 1;
        var paging = PageRequest.of(pageForDB, elementOnPage, Sort.by("deadLine"));
        return taskRepository.findByWorkdayAndEmployees_NameAndEmployees_Department_Id(date, department.getAuthUserFunction(), department.getId(), paging);
    }

    @Override
    @Transactional
    public void save(Task task) {
        var employees = new ArrayList<>(task.getEmployees());
        task.setCondition(State.IN_PROCESS);
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
    public List<Task> getByPeriod(PeriodDto periodDto, Integer page) {
        var pageForDB = page - 1;
        Pageable paging = PageRequest.of(pageForDB, elementOnPage, Sort.by("workday"));
        return taskRepository.findDistinctByWorkdayBetweenAndEmployees_Department_Id(periodDto.getFromDate(), periodDto.getToDate(), periodDto.getDepartmentId(), paging);
    }

    @Override
    public List<Task> getByLastWeek(Long departmentId, Integer page) {
        var pageForDB = page - 1;
        Pageable paging = PageRequest.of(pageForDB, elementOnPage, Sort.by("workday"));
        var today = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        var localToday = dateConverter.convertDateToLocal(today);
        var localLastWeekDay = localToday.minusWeeks(1);
        var lastWeekDay = dateConverter.convertLocalToDate(localLastWeekDay);
        return taskRepository.findDistinctByWorkdayBetweenAndEmployees_Department_Id(lastWeekDay, today, departmentId, paging);
    }

    @Override
    public List<Task> getFilteredTask(WorkDayWithDepartmentIdDto workDayWithDepartmentIdDto) {
        var department = departmentService.findById(workDayWithDepartmentIdDto.getDepartmentId());
        var filteredTasks = filterByDate(workDayWithDepartmentIdDto);
        var doubleFilteredTasks = filterByExecutorAndDate(workDayWithDepartmentIdDto);
        if (!department.getAuthUserFunction().equalsIgnoreCase("manager")){
            for (var task:doubleFilteredTasks) {
                if(task.getDeadLine().toInstant().isBefore(Instant.now()) && !task.getCondition().equals(State.DONE)){
                    fail(task);
                }
            }
            return doubleFilteredTasks;
        } else {
            for (var task:filteredTasks) {
                if(task.getDeadLine().toInstant().isBefore(Instant.now()) && !task.getCondition().equals(State.DONE)){
                    fail(task);
                }
            }
            return filteredTasks;}
    }

    @Override
    public void execute(TaskDto taskDto) {
        var task = taskRepository.getReferenceById(taskDto.getId());
        task.setCondition(State.CONFIRMED);
        taskRepository.saveAndFlush(task);
    }

    @Override
    @Transactional
    public void confirm(TaskDto taskDto) {
        var task = taskRepository.getReferenceById(taskDto.getId());
        task.setCondition(State.DONE);
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
        task.setCondition(State.IN_PROCESS);
        taskRepository.saveAndFlush(task);
    }

    @Override
    public void fail(Task task) {
        task.setCondition(State.FAILED);
        taskRepository.saveAndFlush(task);
    }

    @Override
    public int getCountPageForPeriod(PeriodDto periodDto) {
        var countTask = taskRepository.countDistinctTaskByWorkdayBetweenAndEmployees_Department_Id(periodDto.getFromDate(), periodDto.getToDate(), periodDto.getDepartmentId());
        var countTaskDouble = (double) countTask;
        return (int) Math.ceil(countTaskDouble/elementOnPage);
    }

    @Override
    public int getCountPageForWeek(Long departmentId) {
        var today = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        var localToday = dateConverter.convertDateToLocal(today);
        var localLastWeekDay = localToday.minusWeeks(1);
        var lastWeekDay = dateConverter.convertLocalToDate(localLastWeekDay);
        var countTask = taskRepository.countDistinctTaskByWorkdayBetweenAndEmployees_Department_Id(lastWeekDay, today, departmentId);
        var countTaskDouble = (double) countTask;
        return (int) Math.ceil(countTaskDouble/elementOnPage);
    }

    @Override
    public int getCountPageByWorkday(WorkDayWithDepartmentIdDto workday) {
        var date = Objects.requireNonNullElseGet(workday.getDate(), () -> Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        var department = departmentService.findById(workday.getDepartmentId());
        int countTask;
        if (!department.getAuthUserFunction().equalsIgnoreCase("manager")){
            countTask = taskRepository.countDistinctByWorkdayAndEmployees_Department_Id(date, workday.getDepartmentId());
        } else {
            countTask = taskRepository.countByWorkdayAndEmployees_NameAndEmployees_Department_Id(date, department.getAuthUserFunction(), department.getId());
        }
        return (int) Math.ceil((double) countTask/elementOnPage);
    }
}
