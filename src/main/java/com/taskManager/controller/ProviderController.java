package com.taskManager.controller;

import com.taskManager.service.DepartmentService;
import com.taskManager.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ProviderController {
    private final ProviderService providerService;
    private final DepartmentService departmentService;

    @GetMapping(value = "/provider")
    public String getProviders(@RequestParam("department_id") long id, @NotNull Model model){
        var department = departmentService.findById(id);
        var providers = departmentService.getDepartmentProviders(id);
        model.addAttribute("department", department);
        model.addAttribute("providers", providers);
        model.addAttribute("providerError", "You already have such provider!");
        return "provider";
    }
}
