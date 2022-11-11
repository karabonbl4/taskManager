package com.taskManager.service;

import com.taskManager.model.dto.EmployeeDto;

public interface MailSender {
    void sendInvoice(String to, String link);
    String createInvoiceLinkByEmployee(EmployeeDto employeeDto);
}
