package com.taskManager.controller;

import com.taskManager.service.DepartmentService;
import com.taskManager.service.MaterialService;
import com.taskManager.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MaterialController {
    private final MaterialService materialService;
    private final DepartmentService departmentService;

    @GetMapping(value = "/material")
    public String getProviders(@RequestParam("department_id") long id, @NotNull Model model){
        var department = departmentService.findById(id);
        var materials = department.getMaterials();
        model.addAttribute("department", departmentService.convertDepartmentToDepartmentDto(department));
        model.addAttribute("materials", materials);
        model.addAttribute("materialError", "You already have such material!");
        return "material";
    }
}
