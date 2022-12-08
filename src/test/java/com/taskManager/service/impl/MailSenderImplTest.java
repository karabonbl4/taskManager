package com.taskManager.service.impl;

import com.taskManager.service.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class MailSenderImplTest {
    private final static String TO = "example@mail.com";
    private final static String FROM = "karabonbl4@gmail.com";
    private final static String LINK = "some:link";
    private final static String SUBJECT = "Invoice";
    private final static StringBuilder TEXT = new StringBuilder("You are invited to be part of the team:");
    @Mock
    private JavaMailSender mailSender;
    @InjectMocks
    private MailSenderImpl mailSenderImpl;
    @Test
    @DisplayName("Prepare and send invoice within mailSender")
    void sendInvoice() {
        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setText(TEXT +"\n"+ LINK);
        mailMessage.setTo(TO);
        mailMessage.setFrom(FROM);
        mailMessage.setSubject(SUBJECT);
        mailSenderImpl.sendInvoice(TO, LINK);

        verify(mailSender).send(mailMessage);
    }

    @Test
    @DisplayName("Create link for offer")
    void createInvoiceLinkByEmployee() {
        String host = "http://localhost:";
        ReflectionTestUtils.setField(mailSenderImpl, "host", host);
        String port = "8080";
        ReflectionTestUtils.setField(mailSenderImpl, "port", port);
        String emailFrom = "karabonbl4@gmail.com";
        ReflectionTestUtils.setField(mailSenderImpl, "emailFrom", emailFrom);
        final EmployeeDto employeeDtoForCreateInvoiceLink = new EmployeeDto();
        employeeDtoForCreateInvoiceLink.setDepartmentId(11L);
        employeeDtoForCreateInvoiceLink.setJobTitle("worker");
        employeeDtoForCreateInvoiceLink.setEmail(TO);

        String expected = "http://localhost:8080/invoiceHandler?departmentId=11&name=worker&email=example@mail.com";
        String actual = mailSenderImpl.createInvoiceLinkByEmployee(employeeDtoForCreateInvoiceLink);

        assertEquals(expected, actual);
    }
}