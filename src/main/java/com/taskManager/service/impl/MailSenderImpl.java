package com.taskManager.service.impl;

import com.taskManager.model.dto.EmployeeDto;
import com.taskManager.service.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderImpl implements MailSender {
    private final JavaMailSender mailSender;
    private final StringBuilder invoiceText = new StringBuilder("You are invited to be part of the team:");
    private final String subject = new String("Invoice");
    @Value("${app.host}")
    private String host;
    @Value("${server.port}")
    private String port;
    @Override
    public void sendInvoice(String to, String link) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("karabonbl4@gmail.com");
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(invoiceText +"\n"+ link);
        mailSender.send(mailMessage);
    }

    @Override
    public String createInvoiceLinkByEmployee(EmployeeDto employeeDto) {
        String link = host.concat(port)
                .concat("/invoiceHandler?")
                .concat("department_id=" + employeeDto.getDepartmentId().toString())
                .concat("&name=" + employeeDto.getJobTitle())
                .concat("&email=" + employeeDto.getEmail());
        return link;
    }
}
