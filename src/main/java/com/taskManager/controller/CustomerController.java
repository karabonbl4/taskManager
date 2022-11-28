package com.taskManager.controller;

import com.taskManager.service.CustomerService;
import com.taskManager.service.DepartmentService;
import com.taskManager.service.dto.CustomerDto;
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
public class CustomerController {
    private final CustomerService customerService;
    private final DepartmentService departmentService;

    @GetMapping(value = "/customer")
    public String getCustomer(@RequestParam long departmentId,  @NotNull Model model){
        var department = departmentService.findById(departmentId);
        var customers = departmentService.getDepartmentCustomers(departmentId);
        var workDayWithDepartmentIdDto = departmentService.getWorkdayToday();
        model.addAttribute("department", department);
        model.addAttribute("customers", customers);
        model.addAttribute("workDayWithDepartmentIdDto", workDayWithDepartmentIdDto);
        model.addAttribute("editCustomer", new CustomerDto());
        return "customer";
    }
    @GetMapping(value = "/createCustomer")
    public String getCreateForm(@RequestParam long departmentId, @NotNull Model model){
        var department = departmentService.findById(departmentId);
        model.addAttribute("department", department);
        model.addAttribute("newCustomer", new CustomerDto());
        return "createCustomer";
    }
    @PostMapping(value = "/createCustomer")
    public String createNewCustomer(@ModelAttribute("newCustomer") CustomerDto newCustomer, @NotNull Model model){
        if (!customerService.save(customerService.convertToCustomer(newCustomer))){
            var department = departmentService.findById(newCustomer.getDepartmentId());
            model.addAttribute("customerError", "Customer with that tax number already exist");
            model.addAttribute("department", department);
            model.addAttribute("newCustomer", new CustomerDto());
            return "createCustomer";
        }
        return "redirect:/customer?departmentId=".concat(newCustomer.getDepartmentId().toString());
    }
    @GetMapping(value = "/editCustomer", params = "edit")
    public String getEditCustomerForm(@ModelAttribute("editCustomer") @NotNull CustomerDto editCustomer, @NotNull Model model){
        var department = departmentService.findById(editCustomer.getDepartmentId());
        var customers = departmentService.getDepartmentCustomers(department.getId());
        model.addAttribute("department", department);
        model.addAttribute("customers", customers);
        model.addAttribute("editCustomer", editCustomer);
        return "editCustomer";
    }
    @GetMapping(value = "/editCustomer", params = "delete")
    public String deleteCustomer(@ModelAttribute CustomerDto editCustomer, Model model){
        customerService.delete(editCustomer);
        return "redirect:/customer?departmentId=".concat(editCustomer.getDepartmentId().toString());
    }
    @PostMapping(value = "/editCustomer")
    public String editCustomer(@ModelAttribute("editCustomer") CustomerDto editCustomer, Model model){
        if(!customerService.update(editCustomer)){
            model.addAttribute("customerError", "Change one of some rows");
            model.addAttribute("editCustomer", editCustomer);
            return "editCustomer";
        }
        return "redirect:/customer?departmentId=".concat(editCustomer.getDepartmentId().toString());
    }
}
