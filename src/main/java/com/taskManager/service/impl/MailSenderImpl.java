package com.taskManager.service.impl;

import com.taskManager.service.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderImpl implements MailSender {
    private final JavaMailSender mailSender;
    private final StringBuilder invoiceText = new StringBuilder("You are invited to be part of the team:");
    @Override
    public void sendInvoice(String to, String subject, String link) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("karabonbl4@gmail.com");
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(invoiceText +"\n"+ link);
        mailSender.send(mailMessage);
    }
}
