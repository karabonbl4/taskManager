package com.taskManager.controller;

import com.taskManager.service.DepartmentService;
import com.taskManager.service.TaskService;
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
        var filteredTasks = taskService.getFilteredTask(workDayWithDepartmentIdDto);
        model.addAttribute("tasks", filteredTasks);
        model.addAttribute("department", department);
        model.addAttribute("workDayWithDepartmentIdDto", workDayWithDepartmentIdDto);
        model.addAttribute("editTask", new TaskDto());
        return "task";
    }
    @PostMapping(value = "/task")
    public String getTaskByDate(@ModelAttribute @NotNull WorkDayWithDepartmentIdDto workDayWithDepartmentIdDto, @NotNull Model model) {
        var department = departmentService.findById(workDayWithDepartmentIdDto.getDepartmentId());
        var filteredTasks = taskService.getFilteredTask(workDayWithDepartmentIdDto);
        model.addAttribute("tasks", filteredTasks);
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
        model.addAttribute("newTask", new TaskDto());
        return "createTask";
    }
    @PostMapping(value = "/createTask")
    public String createNewTask(@ModelAttribute @NotNull TaskDto newTask, @RequestParam long departmentId, @NotNull Model model) {
        var department = departmentService.findById(departmentId);
        var workDayWithDepartmentIdDto = new WorkDayWithDepartmentIdDto(newTask.getWorkday(), departmentId);
        taskService.save(taskConverter.convertToTask(newTask));
        model.addAttribute("department", department);
        model.addAttribute("workDayWithDepartmentIdDto", workDayWithDepartmentIdDto);
        return "redirect:/task?departmentId=".concat(newTask.getDepartmentId().toString());
    }
    @GetMapping(value = "/editTask", params = "edit")
    public String getComparableFormTask(@ModelAttribute @NotNull TaskDto editTask, @NotNull Model model){
        var department = departmentService.findById(editTask.getDepartmentId());
        var employees = departmentService.getDepartmentEmployees(editTask.getDepartmentId());
        model.addAttribute("employees", employees);
        model.addAttribute("department", department);
        model.addAttribute("editTask", editTask);
        return "editTask";
    }

    @PostMapping(value = "/editTask")
    public String editTask(@ModelAttribute @NotNull TaskDto editTask, @NotNull Model model){
        var department = departmentService.findById(editTask.getDepartmentId());
        var employees = departmentService.getDepartmentEmployees(editTask.getDepartmentId());
        if (!taskService.update(editTask)){
            model.addAttribute("editTask", editTask);
            model.addAttribute("employees", employees);
            model.addAttribute("department", department);
            model.addAttribute("taskError", "Need to change something row");
            return "editTask";
        }
        return "redirect:/task?departmentId=".concat(editTask.getDepartmentId().toString());
    }
    @GetMapping(value = "/editTask", params = "execute")
    public String executeTask(@ModelAttribute @NotNull TaskDto editTask){
        taskService.execute(editTask);
        return "redirect:/task?departmentId=".concat(editTask.getDepartmentId().toString());
    }
    @GetMapping(value = "/editTask", params = "confirm")
    public String confirmTask(@ModelAttribute @NotNull TaskDto editTask){
        taskService.confirm(editTask);
        return "redirect:/task?departmentId=".concat(editTask.getDepartmentId().toString());
    }
    @GetMapping(value = "/editTask", params = "toWork")
    public String toWorkTask(@ModelAttribute @NotNull TaskDto editTask){
        taskService.toWork(editTask);
        return "redirect:/task?departmentId=".concat(editTask.getDepartmentId().toString());
    }


}
