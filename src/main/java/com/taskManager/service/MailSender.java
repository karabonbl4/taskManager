package com.taskManager.service;

public interface MailSender {
    void sendInvoice(String to, String subject, String link);
}
