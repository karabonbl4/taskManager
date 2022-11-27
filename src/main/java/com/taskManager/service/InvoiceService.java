package com.taskManager.service;

import com.taskManager.service.dto.EmployeeDto;

public interface InvoiceService {
    EmployeeDto invitedEmployee(String jobTitle, Long departmentId);

}
