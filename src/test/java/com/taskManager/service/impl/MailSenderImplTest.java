//package com.taskManager.service.impl;
//
//import com.taskManager.model.entity.User;
//import com.taskManager.model.repository.UserRepository;
//import com.taskManager.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.mail.javamail.JavaMailSender;
//
//import static org.junit.jupiter.api.Assertions.*;
//@ExtendWith(MockitoExtension.class)
//@RequiredArgsConstructor
//class MailSenderImplTest {
//    @Mock
//    private UserRepository userRepository;
//    @InjectMocks
//    private final JavaMailSender mailSender;
//    private User user;
//    @BeforeEach
//    void setUp() {
//        user = new User();
//        userRepository.findByEmail("ivanov@mail.ru").setEmail("karabonbl4@gmail.com");
//
//    }
//
//    @Test
//    void sendInvoice() {
//
//    }
//}