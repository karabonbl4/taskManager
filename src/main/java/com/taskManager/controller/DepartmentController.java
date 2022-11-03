package com.taskManager.controller;

import com.taskManager.model.entity.Department;
import com.taskManager.service.DepartmentService;
import com.taskManager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final UserService userService;

    @GetMapping("/department")
    public String getDepartment(Model model){
        List<Department> departments = departmentService.findByUser(userService.getAuthUser());
        model.addAttribute("departments", departments);
        model.addAttribute("newDepartment", new Department());
        return "department";
    }

    @PostMapping("/department")
    public String addDepartment(@ModelAttribute("newDepartment") Department newDepartment){
        newDepartment.setUser(userService.getAuthUser());
        departmentService.save(newDepartment);
        return "department";
    }
}
