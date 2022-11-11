package com.taskManager.controller;

import com.taskManager.service.CustomerService;
import com.taskManager.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final DepartmentService departmentService;

    @GetMapping(value = "/customer")
    public String getCustomer(@RequestParam(value = "department_id") long id, @NotNull Model model){
        var department = departmentService.findById(id);
        var customers = department.getCustomers();
        model.addAttribute("department", departmentService.convertDepartmentToDepartmentDto(department));
        model.addAttribute("customers", customers);
        model.addAttribute("customerError", "You already have such customer!");
        return "customer";
    }
}
