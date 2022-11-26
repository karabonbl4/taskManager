package com.taskManager.controller;

import com.taskManager.service.DepartmentService;
import com.taskManager.service.MaterialService;
import com.taskManager.service.dto.MaterialDto;
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
        model.addAttribute("editMaterial", new MaterialDto());
        return "material";
    }
    @GetMapping(value = "/createMaterial")
    public String getCreateForm(@RequestParam long departmentId, @NotNull Model model){
        var department = departmentService.findById(departmentId);
        model.addAttribute("department", department);
        model.addAttribute("newMaterial", new MaterialDto());
        model.addAttribute("editMaterial", new MaterialDto());
        return "createMaterial";
    }
    @PostMapping(value = "/createMaterial")
    public String createNewMaterial(@ModelAttribute(value = "newMaterial") MaterialDto newMaterial, @NotNull Model model){
        if (!materialService.save(materialService.convertToMaterial(newMaterial))){
            model.addAttribute("materialError", "Material with that title and property already exist");
            var department = departmentService.findById(newMaterial.getDepartmentId());
            model.addAttribute("department", department);
            return "createMaterial";
        }
        return "redirect:/department";
    }
    @GetMapping(value = "/editMaterial")
    public String getEditMaterialForm(@ModelAttribute("editMaterial") @NotNull MaterialDto editMaterial, @NotNull Model model){
        var department = departmentService.findById(editMaterial.getDepartmentId());
        var materials = departmentService.getDepartmentMaterials(department.getId());
        model.addAttribute("editMaterial", editMaterial);
        model.addAttribute("department", department);
        model.addAttribute("materials", materials);
        model.addAttribute("editMaterial", editMaterial);
        return "editMaterial";
    }
    @PostMapping(value = "/editMaterial")
    public String editMaterial(@ModelAttribute("editMaterial") MaterialDto editMaterial, Model model) {
        if (!materialService.update(editMaterial)) {
            model.addAttribute("materialError", "Change one of some rows");
            var department = departmentService.findById(editMaterial.getDepartmentId());
            model.addAttribute("editMaterial", editMaterial);
            model.addAttribute("department", department);
            return "editMaterial";
        }
        return "redirect:/material?departmentId=".concat(editMaterial.getDepartmentId().toString());
    }
}
