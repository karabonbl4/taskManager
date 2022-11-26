package com.taskManager.controller;

import com.taskManager.service.DepartmentService;
import com.taskManager.service.TaskService;
import com.taskManager.service.dto.TaskCreateDto;
import com.taskManager.service.dto.TaskDto;
import com.taskManager.service.dto.WorkDayWithDepartmentIdDto;
import com.taskManager.service.converter.TaskConverter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final DepartmentService departmentService;
    private final TaskConverter taskConverter;

    @GetMapping(value = "/task")
    public String getTask(@ModelAttribute @NotNull WorkDayWithDepartmentIdDto workDayWithDepartmentIdDto, @NotNull Model model){
        var department = departmentService.findById(workDayWithDepartmentIdDto.getDepartmentId());
        var workday = workDayWithDepartmentIdDto.getDate();
        var filteredTasks = taskService.filterByDate(taskService.findByDepartmentId(workDayWithDepartmentIdDto.getDepartmentId()), workday);
        var doubleFilteredTasks = taskService.filterByExecutorAndDate(taskService.findByDepartmentId(workDayWithDepartmentIdDto.getDepartmentId()), department.getAuthUserFunction(), workday);
        if (!department.getAuthUserFunction().equalsIgnoreCase("manager")){
            model.addAttribute("tasks", doubleFilteredTasks);
        } else {
        model.addAttribute("tasks", filteredTasks);}
        model.addAttribute("department", department);
        model.addAttribute("workDayWithDepartmentIdDto", workDayWithDepartmentIdDto);
        model.addAttribute("editTask", new TaskCreateDto());
        return "task";
    }
    @PostMapping(value = "/task")
    public String getTaskByDate(@ModelAttribute @NotNull WorkDayWithDepartmentIdDto workDayWithDepartmentIdDto, @NotNull Model model) {
        var workday = workDayWithDepartmentIdDto.getDate();

        var department = departmentService.findById(workDayWithDepartmentIdDto.getDepartmentId());
        var filteredTasks = taskService.filterByDate(taskService.findByDepartmentId(workDayWithDepartmentIdDto.getDepartmentId()), workday);
        var doubleFilteredTasks = taskService.filterByExecutorAndDate(taskService.findByDepartmentId(workDayWithDepartmentIdDto.getDepartmentId()), department.getAuthUserFunction(), workday);
        if (department.getAuthUserFunction().equalsIgnoreCase("manager")){
            model.addAttribute("tasks", filteredTasks);
        } else {
            model.addAttribute("tasks", doubleFilteredTasks);}
        model.addAttribute("department", department);
        model.addAttribute("workDayWithDepartmentIdDto", workDayWithDepartmentIdDto);
        model.addAttribute("editTask", new TaskDto());
        return "task";
    }
    @GetMapping(value = "/createTask")
    public String getFormNewTask(@RequestParam long departmentId, @NotNull Model model){
        var department = departmentService.findById(departmentId);
        var employees = departmentService.getDepartmentEmployees(departmentId);
        model.addAttribute("employees", employees);
        model.addAttribute("department", department);
        model.addAttribute("newTask", new TaskCreateDto());
        return "createTask";
    }
    @PostMapping(value = "/createTask")
    public String createNewTask(@ModelAttribute @NotNull TaskCreateDto newTask, @RequestParam long departmentId, @NotNull Model model) {
        var department = departmentService.findById(departmentId);
        var workday = newTask.getWorkday();
        var workDayWithDepartmentIdDto = new WorkDayWithDepartmentIdDto();
        var taskForSave = taskConverter.convertToTask(newTask);
        taskService.save(taskForSave);
        workDayWithDepartmentIdDto.setDepartmentId(departmentId);
        workDayWithDepartmentIdDto.setDate(workday);
        model.addAttribute("department", department);
        model.addAttribute("workDayWithDepartmentIdDto", workDayWithDepartmentIdDto);
        return "redirect:/department";
    }
    @GetMapping(value = "/editTask")
    public String editTask(@ModelAttribute(value = "editTask") TaskDto editTask, Model model){
        var department = departmentService.findById(editTask.getDepartmentId());
        var employees = departmentService.getDepartmentEmployees(editTask.getDepartmentId());
        model.addAttribute("employees", employees);
        model.addAttribute("department", department);
        model.addAttribute("editTask", editTask);
        return "editTask";
    }
}
