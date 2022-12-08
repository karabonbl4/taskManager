package com.taskManager.service.impl;

import com.taskManager.model.entity.Employee;
import com.taskManager.model.entity.Task;
import com.taskManager.model.repository.TaskRepository;
import com.taskManager.service.DepartmentService;
import com.taskManager.service.EmployeeService;
import com.taskManager.service.TempMaterialService;
import com.taskManager.service.converter.TaskConverter;
import com.taskManager.service.dto.DepartmentDto;
import com.taskManager.service.dto.TaskDto;
import com.taskManager.service.dto.WorkDayWithDepartmentIdDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

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
    private static final Long ID = 1L;
    private static final Integer ELEMENT_ON_PAGE = 5;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private TaskConverter taskConverter;
    @Mock
    private TempMaterialService tempMaterialService;
    @Mock
    private DepartmentService departmentService;
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
        ReflectionTestUtils.setField(taskService, "elementOnPage", ELEMENT_ON_PAGE);
        final WorkDayWithDepartmentIdDto workday = new WorkDayWithDepartmentIdDto();
        var page = 1;
        var today = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        workday.setPage(page);
        workday.setDate(today);
        workday.setDepartmentId(DEPARTMENT_ID);
        final List<Task> tasks = new ArrayList<>();
        final List<Task> actualTasks = taskService.filterByDate(workday);

        assertEquals(tasks, actualTasks);
    }

    @Test
    void filterByExecutorAndDate() {
        ReflectionTestUtils.setField(taskService, "elementOnPage", ELEMENT_ON_PAGE);
        var page = 1;
        var today = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        final WorkDayWithDepartmentIdDto workday = new WorkDayWithDepartmentIdDto(today, DEPARTMENT_ID, page);
        final DepartmentDto departmentDto = mock(DepartmentDto.class);

        when(departmentService.findById(DEPARTMENT_ID)).thenReturn(departmentDto);
        when(departmentDto.getId()).thenReturn(DEPARTMENT_ID);
        final List<Task> tasks = new ArrayList<>();
        final List<Task> actualTasks = taskService.filterByExecutorAndDate(workday);

        assertEquals(tasks, actualTasks);
    }

    @Test
    void save() {
        final Task task = mock(Task.class);
        final List<Employee> employees = task.getEmployees();
        final Employee employee = mock(Employee.class);
        employees.add(employee);
        when(task.getEmployees()).thenReturn(employees);
        when(taskRepository.saveAndFlush(task)).thenReturn(task);
        taskService.save(task);

        verify(employeeService).saveAll(employees);
    }

    @Test
    void update() {
        final TaskDto taskDto = mock(TaskDto.class);
        final Task task = mock(Task.class);

        when(taskDto.getId()).thenReturn(ID);
        when(taskRepository.getReferenceById(ID)).thenReturn(task);
        when(taskConverter.convertToTask(taskDto)).thenReturn(task);
        boolean actualFalse = taskService.update(taskDto);

        assertFalse(actualFalse);

        final Task otherTask = mock(Task.class);
        lenient().when(taskConverter.convertToTask(taskDto)).thenReturn(otherTask);
        lenient().when(taskRepository.saveAndFlush(otherTask)).thenReturn(otherTask);
        boolean actualTrue = taskService.update(taskDto);

        assertTrue(actualTrue);
    }

//    @Test
//    void getByPeriod() {
//    }
//
//    @Test
//    void getByLastWeek() {
//    }

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