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

    @GetMapping(value = "/department")
    public String getDepartment(@NotNull Model model){
        var departments = departmentService.getDepartmentsDto();
        var workDayWithDepartmentIdDto = departmentService.getWorkdayToday();
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

    @GetMapping(value = "/editDepartment")
    public String getDepartmentEditor(@RequestParam(value = "departmentId") Long id, @NotNull Model model){
        var editDepartment = departmentService.findById(id);
        model.addAttribute("editDepartment", editDepartment);
        model.addAttribute("department", editDepartment);
        return "editDepartment";
    }
    @PostMapping(value = "/editDepartment")
    public String updateDepartment(@ModelAttribute DepartmentDto editDepartment, Model model){
       if(!departmentService.update(editDepartment)){
           model.addAttribute("departmentError", "Change one of some rows");
           model.addAttribute("editDepartment", editDepartment);
           model.addAttribute("department", editDepartment);
           return "editDepartment";
       }
       return "redirect:/department";
    }
}
