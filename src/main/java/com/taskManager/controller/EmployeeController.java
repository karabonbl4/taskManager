package com.taskManager.controller;

import com.taskManager.service.dto.EmployeeDto;
import com.taskManager.model.entity.Department;
import com.taskManager.model.entity.Employee;
import com.taskManager.service.DepartmentService;
import com.taskManager.service.EmployeeService;
import com.taskManager.service.MailSender;
import com.taskManager.service.UserService;
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
public class EmployeeController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final MailSender mailSender;
    private final UserService userService;
    private final DateConverter dateConverter;

    @GetMapping(value = "/employee")
    public String getEmployees(@RequestParam("departmentId") long id, @NotNull Model model){
        var workDayWithDepartmentIdDto = new WorkDayWithDepartmentIdDto();                              //какой-то рабочий день по умолчанию
        workDayWithDepartmentIdDto.setDate(dateConverter.convertLocalToDate(LocalDate.now()));          //какой-то рабочий день по умолчанию
        model.addAttribute("employees", departmentService.getDepartmentEmployees(id));
        model.addAttribute("department", departmentService.findById(id));
        model.addAttribute("workDayWithDepartmentIdDto", workDayWithDepartmentIdDto);
        return "employee";
    }
    @GetMapping(value = "/invoiceEmployee")
    public String createInvoiceEmployee(@RequestParam("departmentId") long id, @NotNull Model model){
        var department = departmentService.findById(id);
        var workDayWithDepartmentIdDto = new WorkDayWithDepartmentIdDto();                      //какой-то рабочий день по умолчанию
        workDayWithDepartmentIdDto.setDate(dateConverter.convertLocalToDate(LocalDate.now()));  //какой-то рабочий день по умолчанию
        model.addAttribute("newEmployee", new EmployeeDto());
        model.addAttribute("department",department);
        model.addAttribute("workDayWithDepartmentIdDto", workDayWithDepartmentIdDto);
        return "invoiceEmployee";
    }
    @PostMapping(value = "/invoiceEmployee")
    public String sendInvoiceToUser(@ModelAttribute("newEmployee") @NotNull EmployeeDto invitedEmployee, @NotNull Model model){
        var department = departmentService.findById(invitedEmployee.getDepartmentId());         //переделать
        var invoiceLinkByEmployee = mailSender.createInvoiceLinkByEmployee(invitedEmployee);        //
        var workDayWithDepartmentIdDto = new WorkDayWithDepartmentIdDto();                                  //
        workDayWithDepartmentIdDto.setDate(dateConverter.convertLocalToDate(LocalDate.now()));              //
        mailSender.sendInvoice(invitedEmployee.getEmail(), invoiceLinkByEmployee);                          //
        model.addAttribute("department", department);
        model.addAttribute("workDayWithDepartmentIdDto", workDayWithDepartmentIdDto);
        return "employee";
    }
    @GetMapping(value = "/invoiceHandler")
    public String registerNewEmployee(@RequestParam("departmentId") String id,
                                      @RequestParam("name") String jobTitle,
                                      @NotNull Model model){
        var department = departmentService.findById(Long.parseLong(id));

        var employeeDto = new EmployeeDto();                                                                // нужно перенести в сервиса
        employeeDto.setJobTitle(jobTitle);                                                                  // нужно перенести в сервиса
        employeeDto.setDepartmentId(department.getId());                                                    //
        employeeDto.setUsername(userService.getAuthUser().getUsername());                                   //
        model.addAttribute("newEmployee", employeeDto);
        model.addAttribute("department", department);
        return "invoiceHandler";
    }
    @PostMapping(value = "/invoiceHandler", params="submit")
    public String handleInvoice(@ModelAttribute("newEmployee") EmployeeDto newEmployee, Model model){
        var department = departmentService.findById(newEmployee.getDepartmentId());
        var user = userService.getAuthUser();
        var employee = new Employee();                                                                  //нужно перенести в сервиса
        employee.setName(newEmployee.getJobTitle());
        employee.setUser(user);
        employee.setDepartment(departmentService.getById(department.getId()));
        employeeService.save(employee);                                                                 //
        model.addAttribute("department", department);
        return "redirect:/department"; //не откроет
    }
    @PostMapping(value = "/invoiceHandler", params="cancel")
    public String backToDepartment(@ModelAttribute("newEmployee") EmployeeDto newEmployee, Model model){
        var departments = departmentService.getDepartmentsDto();
        model.addAttribute("departments", departments);
        model.addAttribute("newDepartment", new Department()); //overwrite to deptDto
        return "redirect:/department";
    }
}
