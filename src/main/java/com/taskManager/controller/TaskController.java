package com.taskManager.controller;

import com.taskManager.service.DepartmentService;
import com.taskManager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final DepartmentService departmentService;

    @GetMapping(value = "/task")
    public String getTask(@RequestParam("department_id") long id, @RequestParam("calendar") String workDay, @NotNull Model model){
        var department = departmentService.findById(id);
        Date date;
        if (workDay.equals("")){
            date = Date.valueOf(LocalDate.now());
        } else {
            date = Date.valueOf(workDay);
        }
        var filteredTasks = taskService.filterByDate(taskService.findByDepartmentId(id), date);
        model.addAttribute("tasks", filteredTasks);
        model.addAttribute("department", departmentService.convertDepartmentToDepartmentDto(department));
        return "task";
    }
}
