package com.taskManager.controller;

import com.taskManager.service.DepartmentService;
import com.taskManager.service.MaterialService;
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
public class MaterialController {
    private final MaterialService materialService;
    private final DepartmentService departmentService;
    private final DateConverter dateConverter;

    @GetMapping(value = "/material")
    public String getProviders(@RequestParam("departmentId") long id, @NotNull Model model){
        var department = departmentService.findById(id);
        var materials = departmentService.getDepartmentMaterials(id);
        var workDayWithDepartmentIdDto = new WorkDayWithDepartmentIdDto();
        workDayWithDepartmentIdDto.setDate(dateConverter.convertLocalToDate(LocalDate.now()));
        model.addAttribute("department", department);
        model.addAttribute("materials", materials);
        model.addAttribute("materialError", "You already have such material!");
        model.addAttribute("workDayWithDepartmentIdDto", workDayWithDepartmentIdDto);
        return "material";
    }
}
