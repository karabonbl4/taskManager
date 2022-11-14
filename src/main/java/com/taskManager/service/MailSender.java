package com.taskManager.service;

import com.taskManager.service.dto.EmployeeDto;

public interface MailSender {
    void sendInvoice(String to, String link);
    String createInvoiceLinkByEmployee(EmployeeDto employeeDto);
}
