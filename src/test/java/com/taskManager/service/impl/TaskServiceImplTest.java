package com.taskManager.service.impl;

import com.taskManager.model.entity.Employee;
import com.taskManager.model.entity.Task;
import com.taskManager.model.entity.TempMaterial;
import com.taskManager.model.repository.TaskRepository;
import com.taskManager.service.DepartmentService;
import com.taskManager.service.EmployeeService;
import com.taskManager.service.TempMaterialService;
import com.taskManager.service.converter.DateConverter;
import com.taskManager.service.converter.TaskConverter;
import com.taskManager.service.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class TaskServiceImplTest {
    private static final Date WORKDAY = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    private static final Long DEPARTMENT_ID = 11L;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private TaskConverter taskConverter;
    @Mock
    private DepartmentService departmentService;
    @Mock
    private DateConverter dateConverter;
    @Mock
    private TempMaterialService tempMaterialService;
    @InjectMocks
    private TaskServiceImpl taskService;


    @Test
    void findByDate() {
        final List<Task> tasks = mock(List.class);
        when(taskRepository.findByWorkday(WORKDAY)).thenReturn(tasks);

        final List<Task> returnedTasks = taskService.findByDate(WORKDAY);

        assertNotNull(tasks);
        assertEquals(tasks, returnedTasks);
        verify(taskRepository).findByWorkday(WORKDAY);
    }

    @Test
    void findByDepartmentId() {
        final List<Task> tasks = mock(List.class);
        when(taskRepository.findByEmployees_Department_Id(DEPARTMENT_ID)).thenReturn(tasks);

        final List<Task> returnedTasks = taskService.findByDepartmentId(DEPARTMENT_ID);

        assertNotNull(tasks);
        assertEquals(tasks, returnedTasks);
        verify(taskRepository).findByEmployees_Department_Id(DEPARTMENT_ID);
    }

    @Test
    void filterByDate() {

    }

    @Test
    void filterByExecutorAndDate() {
    }

    @Test
    void save() {
        final Task task = mock(Task.class);
        final List<Employee> employees = task.getEmployees();
        final Employee employee = mock(Employee.class);
        final List<TempMaterial> tempMaterials = null;
        employees.add(employee);
        when(task.getEmployees()).thenReturn(employees);
        when(taskRepository.saveAndFlush(task)).thenReturn(task);
        taskService.save(task);

        verify(employeeService).saveAll(employees);
    }

    @Test
    void update() {

    }

    @Test
    void getByPeriod() {
    }

    @Test
    void getByLastWeek() {
    }

    @Test
    void getFilteredTask() {
    }

    @Test
    void execute() {
        final TaskDto taskDto = mock(TaskDto.class);
        final Task task = mock(Task.class);
        when(taskDto.getId()).thenReturn(1L);
        when(taskRepository.getReferenceById(1L)).thenReturn(task);

        taskService.execute(taskDto);

        verify(taskRepository).saveAndFlush(task);
    }

    @Test
    void confirm() {
        final TaskDto taskDto = mock(TaskDto.class);
        final Task task = mock(Task.class);
        when(taskDto.getId()).thenReturn(1L);
        when(taskRepository.getReferenceById(1L)).thenReturn(task);

        taskService.confirm(taskDto);

        verify(taskRepository).saveAndFlush(task);
    }

    @Test
    void toWork() {
        final TaskDto taskDto = mock(TaskDto.class);
        final Task task = mock(Task.class);
        when(taskDto.getId()).thenReturn(1L);
        when(taskRepository.getReferenceById(1L)).thenReturn(task);

        taskService.toWork(taskDto);

        verify(taskRepository).saveAndFlush(task);
    }
}