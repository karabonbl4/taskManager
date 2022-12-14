package com.taskManager.service.impl;

import com.taskManager.service.InvoiceService;
import com.taskManager.service.UserService;
import com.taskManager.service.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final UserService userService;

    @Override
    public EmployeeDto invitedEmployee(String jobTitle, Long departmentId) {
        var invitedEmployee = new EmployeeDto();
        invitedEmployee.setJobTitle(jobTitle);
        invitedEmployee.setDepartmentId(departmentId);
        invitedEmployee.setUsername(userService.getAuthUser().getUsername());
        return invitedEmployee;
    }
}
