package com.taskManager.service.impl;

import com.taskManager.model.entity.User;
import com.taskManager.service.UserService;
import com.taskManager.service.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class InvoiceServiceImplTest {
    private static final String USERNAME = "username";
    private static final String JOB_TITLE = "worker";
    private static final Long DEPARTMENT_ID = 11L;
    @Mock
    private UserService userService;
    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Test
    @DisplayName("Create employeeDto that contained in offer")
    void invitedEmployee() {
        final EmployeeDto invitedEmployee= new EmployeeDto();
        final User user = mock(User.class);
        user.setUsername(USERNAME);
        invitedEmployee.setJobTitle(JOB_TITLE);
        invitedEmployee.setDepartmentId(DEPARTMENT_ID);
        when(userService.getAuthUser()).thenReturn(user);

        final EmployeeDto actual = invoiceService.invitedEmployee(JOB_TITLE, DEPARTMENT_ID);

        assertEquals(invitedEmployee, actual);

        verify(userService).getAuthUser();
    }
}