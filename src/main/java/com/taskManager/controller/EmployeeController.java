package com.taskManager.controller;

import com.taskManager.service.*;
import com.taskManager.service.converter.EmployeeConverter;
import com.taskManager.service.dto.EmployeeDto;
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
public class EmployeeController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final MailSender mailSender;
    private final InvoiceService invoiceService;
    private final EmployeeConverter employeeConverter;

    @GetMapping(value = "/employee")
    public String getEmployees(@RequestParam("departmentId") long id, @NotNull Model model) {
        var workDayWithDepartmentIdDto = departmentService.getWorkdayToday();
        model.addAttribute("employees", departmentService.getDepartmentEmployees(id));
        model.addAttribute("department", departmentService.findById(id));
        model.addAttribute("workDayWithDepartmentIdDto", workDayWithDepartmentIdDto);
        model.addAttribute("editEmployee", new EmployeeDto());
        return "employee";
    }

    @GetMapping(value = "/invoiceEmployee")
    public String createInvoiceEmployee(@RequestParam("departmentId") long id, @NotNull Model model) {
        var department = departmentService.findById(id);
        var workDayWithDepartmentIdDto = departmentService.getWorkdayToday();
        model.addAttribute("newEmployee", new EmployeeDto());
        model.addAttribute("department", department);
        model.addAttribute("workDayWithDepartmentIdDto", workDayWithDepartmentIdDto);
        return "invoiceEmployee";
    }

    @PostMapping(value = "/invoiceEmployee")
    public String sendInvoiceToUser(@ModelAttribute("newEmployee") @NotNull EmployeeDto invitedEmployee, @NotNull Model model) {
        var department = departmentService.findById(invitedEmployee.getDepartmentId());
        var invoiceLinkByEmployee = mailSender.createInvoiceLinkByEmployee(invitedEmployee);
        var workDayWithDepartmentIdDto = departmentService.getWorkdayToday();
        if (departmentService.containEmployee(invitedEmployee)) {
            model.addAttribute("department", department);
            model.addAttribute("workDayWithDepartmentIdDto", workDayWithDepartmentIdDto);
            model.addAttribute("newEmployee", new EmployeeDto());
            model.addAttribute("inviteError", "Employee with this email already exists in this department");
            return "invoiceEmployee";
        }
        mailSender.sendInvoice(invitedEmployee.getEmail(), invoiceLinkByEmployee);
        return "redirect:/employee?departmentId=".concat(department.getId().toString());
    }

    @GetMapping(value = "/invoiceHandler")
    public String registerNewEmployee(@RequestParam("departmentId") String id,
                                      @RequestParam("name") String jobTitle,
                                      @NotNull Model model) {
        var department = departmentService.findById(Long.parseLong(id));
        var invitedEmployee = invoiceService.invitedEmployee(jobTitle, department.getId());
        model.addAttribute("newEmployee", invitedEmployee);
        model.addAttribute("department", department);
        return "invoiceHandler";
    }

    @PostMapping(value = "/invoiceHandler", params = "submit")
    public String handleInvoice(@ModelAttribute("newEmployee") EmployeeDto newEmployee, Model model) {
        var department = departmentService.findById(newEmployee.getDepartmentId());
        employeeService.save(employeeConverter.convertToEmployee(newEmployee));
        model.addAttribute("department", department);
        return "redirect:/department";
    }

    @PostMapping(value = "/invoiceHandler", params = "cancel")
    public String backToDepartment(@ModelAttribute("newEmployee") EmployeeDto newEmployee, Model model) {
        return "redirect:/department";
    }

    @GetMapping(value = "/editEmployee", params = "edit")
    public String getEditEmployeeForm(@ModelAttribute @NotNull EmployeeDto editEmployee, @NotNull Model model) {
        var department = departmentService.findById(editEmployee.getDepartmentId());
        model.addAttribute("department", department);
        model.addAttribute("editEmployee", editEmployee);
        return "editEmployee";
    }

    @PostMapping(value = "/editEmployee")
    public String editEmployee(@ModelAttribute EmployeeDto editEmployee, Model model) {
        if (!employeeService.update(editEmployee)) {
            var department = departmentService.findById(editEmployee.getDepartmentId());
            model.addAttribute("employeeError", "Employee with this job title already exist");
            model.addAttribute("editEmployee", editEmployee);
            model.addAttribute("department", department);
            return "editEmployee";
        }
        return "redirect:/employee?departmentId=".concat(editEmployee.getDepartmentId().toString());
    }

    @GetMapping(value = "/editEmployee", params = "delete")
    public String deleteEmployee(@ModelAttribute EmployeeDto editEmployee, Model model) {
        employeeService.delete(editEmployee);
        return "redirect:/employee?departmentId=".concat(editEmployee.getDepartmentId().toString());
    }

}
