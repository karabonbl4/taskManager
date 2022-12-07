package com.taskManager.service;


import com.taskManager.model.entity.Task;
import com.taskManager.service.dto.PeriodDto;
import com.taskManager.service.dto.TaskDto;
import com.taskManager.service.dto.WorkDayWithDepartmentIdDto;

import java.util.Date;
import java.util.List;

public interface TaskService {
    List<Task> findByDate(Date date);
    List<Task> findByDepartmentId(long id);
    List<Task> filterByToday(WorkDayWithDepartmentIdDto workday);
    List<Task> filterByExecutorAndDate(WorkDayWithDepartmentIdDto workday);
    void save(Task task);
    boolean update(TaskDto taskDto);
    List<Task> getByPeriod(PeriodDto periodDto, Integer page);
    List<Task> getByLastWeek(Long departmentId, Integer page);
    List<Task> getFilteredTask(WorkDayWithDepartmentIdDto workday);
    void execute(TaskDto taskDto);
    void confirm(TaskDto taskDto);
    void toWork(TaskDto taskDto);
    void fail(Task task);
    int getCountPageForPeriod(PeriodDto periodDto);
    int getCountPageForWeek(Long departmentId);
    int getCountPageByWorkday(WorkDayWithDepartmentIdDto workday);


}
