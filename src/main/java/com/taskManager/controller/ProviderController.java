package com.taskManager.controller;

import com.taskManager.service.DepartmentService;
import com.taskManager.service.ProviderService;
import com.taskManager.service.dto.WorkDayWithDepartmentIdDto;
import com.taskManager.service.mapper.DateConverter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
}
