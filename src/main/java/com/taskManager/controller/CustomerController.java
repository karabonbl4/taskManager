package com.taskManager.controller;

import com.taskManager.service.CustomerService;
import com.taskManager.service.DepartmentService;
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
import java.time.ZoneId;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final DepartmentService departmentService;
    private final DateConverter dateConverter;

    @GetMapping(value = "/customer")
    public String getCustomer(@RequestParam long departmentId,  @NotNull Model model){
        var department = departmentService.findById(departmentId);
        var customers = departmentService.getDepartmentCustomers(departmentId);
        var workDayWithDepartmentIdDto = new WorkDayWithDepartmentIdDto();
        workDayWithDepartmentIdDto.setDate(dateConverter.convertLocalToDate(LocalDate.now()));
        model.addAttribute("department", department);
        model.addAttribute("customers", customers);
        model.addAttribute("customerError", "You already have such customer!");
        model.addAttribute("workDayWithDepartmentIdDto", workDayWithDepartmentIdDto);
        return "customer";
    }
}
