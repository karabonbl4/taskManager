package com.taskManager.service.impl;

import com.taskManager.service.MailSender;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class MailSenderImplTest {
    @Mock
    private MailSender mailSender;
    @Mock
    private StringBuilder invoiceText;
    @InjectMocks
    private MailSenderImpl mailSenderImpl;
    @Test
    void sendInvoice() {
    }

    @Test
    void createInvoiceLinkByEmployee() {
    }
}