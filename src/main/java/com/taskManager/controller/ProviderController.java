package com.taskManager.controller;

import com.taskManager.service.CompanyService;
import com.taskManager.service.DepartmentService;
import com.taskManager.service.converter.CompanyConverter;
import com.taskManager.service.dto.ProviderDto;
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
public class ProviderController {
    private final CompanyService companyService;
    private final DepartmentService departmentService;
    private final CompanyConverter companyConverter;

    @GetMapping(value = "/provider")
    public String getProviders(@RequestParam long departmentId, @NotNull Model model) {
        var department = departmentService.findById(departmentId);
        var providers = departmentService.getDepartmentProviders(departmentId);
        var workDayWithDepartmentIdDto = departmentService.getWorkdayToday();
        model.addAttribute("workDayWithDepartmentIdDto", workDayWithDepartmentIdDto);
        model.addAttribute("department", department);
        model.addAttribute("providers", providers);
        model.addAttribute("editProvider", new ProviderDto());
        return "provider";
    }

    @GetMapping(value = "/createProvider")
    public String getCreateForm(@RequestParam long departmentId, @NotNull Model model) {
        var department = departmentService.findById(departmentId);
        model.addAttribute("department", department);
        model.addAttribute("newProvider", new ProviderDto());
        return "createProvider";
    }

    @PostMapping(value = "/createProvider")
    public String createNewProvider(@ModelAttribute(value = "newProvider") ProviderDto newProvider, @NotNull Model model) {
        if (!companyService.save(companyConverter.convertProviderDtoToCompany(newProvider))) {
            var department = departmentService.findById(newProvider.getDepartmentId());
            model.addAttribute("providerError", "Provider with that tax number already exist");
            model.addAttribute("department", department);
            return "createProvider";
        }
        return "redirect:/department";
    }

    @GetMapping(value = "/editProvider", params = "edit")
    public String getEditProviderForm(@ModelAttribute("editProvider") @NotNull ProviderDto editProvider, @NotNull Model model) {
        var department = departmentService.findById(editProvider.getDepartmentId());
        var customers = departmentService.getDepartmentCustomers(department.getId());
        model.addAttribute("department", department);
        model.addAttribute("providers", customers);
        model.addAttribute("editProvider", editProvider);
        return "editProvider";
    }
    @GetMapping(value = "/editProvider", params = "delete")
    public String deleteProvider(@ModelAttribute ProviderDto editProvider, Model model){
        companyService.delete(companyConverter.convertProviderDtoToCompany(editProvider));
        return "redirect:/provider?departmentId=".concat(editProvider.getDepartmentId().toString());
    }

    @PostMapping(value = "/editProvider")
    public String editProvider(@ModelAttribute("editProvider") ProviderDto editProvider, Model model) {
        if (!companyService.update(companyConverter.convertProviderDtoToCompany(editProvider))) {
            var department = departmentService.findById(editProvider.getDepartmentId());
            model.addAttribute("providerError", "Change one of some rows");
            model.addAttribute("editProvider", editProvider);
            model.addAttribute("department", department);
            return "editProvider";
        }
        return "redirect:/provider?departmentId=".concat(editProvider.getDepartmentId().toString());
    }
}
