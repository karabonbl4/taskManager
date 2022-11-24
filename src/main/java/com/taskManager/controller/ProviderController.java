package com.taskManager.controller;

import com.taskManager.service.DepartmentService;
import com.taskManager.service.ProviderService;
import com.taskManager.service.dto.CustomerDto;
import com.taskManager.service.dto.ProviderDto;
import com.taskManager.service.dto.WorkDayWithDepartmentIdDto;
import com.taskManager.service.converter.DateConverter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class ProviderController {
    private final ProviderService providerService;
    private final DepartmentService departmentService;
    private final DateConverter dateConverter;

    @GetMapping(value = "/provider")
    public String getProviders(@RequestParam long departmentId, @NotNull Model model){
        var department = departmentService.findById(departmentId);
        var providers = departmentService.getDepartmentProviders(departmentId);
        var workDayWithDepartmentIdDto = new WorkDayWithDepartmentIdDto();
        workDayWithDepartmentIdDto.setDate(dateConverter.convertLocalToDate(LocalDate.now()));
        model.addAttribute("workDayWithDepartmentIdDto", workDayWithDepartmentIdDto);
        model.addAttribute("department", department);
        model.addAttribute("providers", providers);
        model.addAttribute("providerError", "You already have such provider!");
        return "provider";
    }
    @GetMapping(value = "/createProvider")
    public String getCreateForm(@RequestParam long departmentId, @NotNull Model model){
        var department = departmentService.findById(departmentId);
        model.addAttribute("department", department);
        model.addAttribute("newProvider", new ProviderDto());
        return "createProvider";
    }
    @PostMapping(value = "/createProvider")
    public String createNewProvider(@ModelAttribute(value = "newProvider") ProviderDto newProvider, @NotNull Model model){
        if (!providerService.save(providerService.convertToProvider(newProvider))){
            model.addAttribute("providerError", "Provider with that tax number already exist");
            var department = departmentService.findById(newProvider.getDepartmentId());
            model.addAttribute("department", department);
            return "createProvider";
        }
        return "redirect:/department";
    }
}
