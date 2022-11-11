package com.taskManager.controller;

import com.taskManager.model.entity.Department;
import com.taskManager.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping(value = "/department")
    public String getDepartment(@NotNull Model model){
        var departments = departmentService.getDepartmentsDto();
        model.addAttribute("departments", departments);
        model.addAttribute("newDepartment", new Department());
        return "department";
    }

    @PostMapping(value = "/department")
    public String addDepartment(@ModelAttribute("newDepartment") Department newDepartment, Model model){
        if (!departmentService.save(newDepartment)){
            model.addAttribute("departmentError", "You already have such a department!");
            return "department";
        }
        return "redirect:/department";
    }
    @GetMapping(value = "/departmentDetails")
    public String getDepartmentDetails(@RequestParam long id, @NotNull Model model){
        var departmentDto = departmentService.convertDepartmentToDepartmentDto(departmentService.findById(id));
        model.addAttribute("department", departmentDto);
        return "departmentDetails";
    }

}
