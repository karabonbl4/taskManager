package com.taskManager.controller;

import com.taskManager.service.dto.EmployeeDto;
import com.taskManager.model.entity.Department;
import com.taskManager.model.entity.Employee;
import com.taskManager.service.DepartmentService;
import com.taskManager.service.EmployeeService;
import com.taskManager.service.MailSender;
import com.taskManager.service.UserService;
import com.taskManager.service.dto.WorkDayWithDepartmentIdDto;
import com.taskManager.service.mapper.DateConverter;
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
public class EmployeeController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final MailSender mailSender;
    private final UserService userService;
    private final DateConverter dateConverter;

    @GetMapping(value = "/employee")
    public String getEmployees(@RequestParam("departmentId") long id, @NotNull Model model){
        var department = departmentService.findById(id);
        var employees = departmentService.getDepartmentEmployees(id);
        var workDayWithDepartmentIdDto = new WorkDayWithDepartmentIdDto();
        workDayWithDepartmentIdDto.setDate(dateConverter.convertLocalToDate(LocalDate.now()));
        model.addAttribute("employees", employees);
        model.addAttribute("department", department);
        model.addAttribute("workDayWithDepartmentIdDto", workDayWithDepartmentIdDto);
        return "employee";
    }
    @GetMapping(value = "/invoiceEmployee")
    public String createInvoiceEmployee(@RequestParam("departmentId") long id, @NotNull Model model){
        var department = departmentService.findById(id);
        EmployeeDto invitedEmployee = new EmployeeDto();
        model.addAttribute("newEmployee", invitedEmployee);
        model.addAttribute("department",department);
        return "invoiceEmployee";
    }
    @PostMapping(value = "/invoiceEmployee")
    public String sendInvoiceToUser(@ModelAttribute("newEmployee") EmployeeDto invitedEmployee, Model model){
        var department = departmentService.findById(invitedEmployee.getDepartmentId());
        var invoiceLinkByEmployee = mailSender.createInvoiceLinkByEmployee(invitedEmployee);
        mailSender.sendInvoice(invitedEmployee.getEmail(), invoiceLinkByEmployee);
        model.addAttribute("department", department);
        return "employee";
    }
    @GetMapping(value = "/invoiceHandler")
    public String registerNewEmployee(@RequestParam("departmentId") String id,
                                      @RequestParam("name") String jobTitle,
                                      @RequestParam("email") String email, Model model){
        var department = departmentService.findById(Long.parseLong(id));
        model.addAttribute("department", department);
        var employeeDto = new EmployeeDto();
        employeeDto.setJobTitle(jobTitle);
        employeeDto.setDepartmentId(Long.parseLong(id));
        employeeDto.setUsername(userService.getAuthUser().getUsername());
        model.addAttribute("newEmployee", employeeDto);
        return "invoiceHandler";
    }
    @PostMapping(value = "/invoiceHandler", params="submit")
    public String handleInvoice(@ModelAttribute("newEmployee") EmployeeDto newEmployee, Model model){
        var department = departmentService.findById(newEmployee.getDepartmentId());
        var user = userService.getAuthUser();
        var employee = new Employee();
        employee.setName(newEmployee.getJobTitle());
        employee.setUser(user);
        employee.setDepartment(departmentService.getById(newEmployee.getDepartmentId()));
        employeeService.save(employee);
        model.addAttribute("department", department);
        return "departmentDetails";
    }
    @PostMapping(value = "/invoiceHandler", params="cancel")
    public String backToDepartment(@ModelAttribute("newEmployee") EmployeeDto newEmployee, Model model){
        var departments = departmentService.getDepartmentsDto();
        model.addAttribute("departments", departments);
        model.addAttribute("newDepartment", new Department()); //overwrite to deptDto
        return "department";
    }
}
