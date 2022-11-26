package com.taskManager.controller;

import com.taskManager.service.DepartmentService;
import com.taskManager.service.dto.DepartmentDto;
import com.taskManager.service.dto.WorkDayWithDepartmentIdDto;
import com.taskManager.service.converter.DateConverter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final DateConverter dateConverter;

    @GetMapping(value = "/department")
    public String getDepartment(@NotNull Model model){
        var departments = departmentService.getDepartmentsDto();
        var workDayWithDepartmentIdDto = new WorkDayWithDepartmentIdDto();
        workDayWithDepartmentIdDto.setDate(dateConverter.convertLocalToDate(LocalDate.now()));
        model.addAttribute("departments", departments);
        model.addAttribute("newDepartment", new DepartmentDto());
        model.addAttribute("workDayWithDepartmentIdDto", workDayWithDepartmentIdDto);
        return "department";
    }

    @PostMapping(value = "/department")
    public String addDepartment(@ModelAttribute DepartmentDto newDepartment, Model model){
        if (!departmentService.save(newDepartment)){
            model.addAttribute("departmentError", "You already have such a department or fields is empty!");
            model.addAttribute("newDepartment", new DepartmentDto());
            return "department";
        }
        return "redirect:/department";
    }

}
