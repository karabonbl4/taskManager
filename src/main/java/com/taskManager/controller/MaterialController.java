package com.taskManager.controller;

import com.taskManager.service.DepartmentService;
import com.taskManager.service.MaterialService;
import com.taskManager.service.converter.MaterialConverter;
import com.taskManager.service.dto.MaterialDto;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MaterialController {
    private final MaterialService materialService;
    private final DepartmentService departmentService;
    private final MaterialConverter materialConverter;

    @GetMapping(value = "/material")
    public String getProviders(@RequestParam("departmentId") long id, @NotNull Model model) {
        var department = departmentService.findById(id);
        var materials = departmentService.getDepartmentMaterials(id);
        var workDayWithDepartmentIdDto = departmentService.getWorkdayToday();
        model.addAttribute("department", department);
        model.addAttribute("materials", materials);
        model.addAttribute("materialError", "You already have such material!");
        model.addAttribute("workDayWithDepartmentIdDto", workDayWithDepartmentIdDto);
        model.addAttribute("editMaterial", new MaterialDto());
        return "material";
    }

    @GetMapping(value = "/createMaterial")
    public String getCreateForm(@RequestParam long departmentId, @NotNull Model model) {
        var department = departmentService.findById(departmentId);
        model.addAttribute("department", department);
        model.addAttribute("newMaterial", new MaterialDto());
        model.addAttribute("editMaterial", new MaterialDto());
        return "createMaterial";
    }

    @PostMapping(value = "/createMaterial")
    public String createNewMaterial(@ModelAttribute(value = "newMaterial") MaterialDto newMaterial, @NotNull Model model) {
        if (!materialService.save(materialConverter.convertToMaterial(newMaterial))) {
            var department = departmentService.findById(newMaterial.getDepartmentId());
            model.addAttribute("materialError", "Material with that title and property already exist");
            model.addAttribute("department", department);
            return "createMaterial";
        }
        return "redirect:/department";
    }

    @GetMapping(value = "/editMaterial", params = "edit")
    public String getEditMaterialForm(@ModelAttribute("editMaterial") @NotNull MaterialDto editMaterial, @NotNull Model model) {
        var department = departmentService.findById(editMaterial.getDepartmentId());
        model.addAttribute("editMaterial", editMaterial);
        model.addAttribute("department", department);
        return "editMaterial";
    }
    @GetMapping(value = "/editMaterial", params = "delete")
    public String deleteMaterial(@ModelAttribute MaterialDto editMaterial){
        materialService.delete(editMaterial);
        return "redirect:/material?departmentId=".concat(editMaterial.getDepartmentId().toString());
    }

    @PostMapping(value = "/editMaterial")
    public String editMaterial(@ModelAttribute("editMaterial") MaterialDto editMaterial, Model model) {
        if (!materialService.update(editMaterial)) {
            var department = departmentService.findById(editMaterial.getDepartmentId());
            model.addAttribute("materialError", "Change one of some rows");
            model.addAttribute("editMaterial", editMaterial);
            model.addAttribute("department", department);
            return "editMaterial";
        }
        return "redirect:/material?departmentId=".concat(editMaterial.getDepartmentId().toString());
    }
}
