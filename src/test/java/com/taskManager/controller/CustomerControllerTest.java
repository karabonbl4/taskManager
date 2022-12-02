//package com.taskManager.controller;
//
//import com.taskManager.service.CustomerService;
//import com.taskManager.service.DepartmentService;
//import com.taskManager.service.converter.CustomerConverter;
//import com.taskManager.service.dto.CustomerDto;
//import com.taskManager.service.dto.DepartmentDto;
//import com.taskManager.service.dto.WorkDayWithDepartmentIdDto;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@RequiredArgsConstructor
//class CustomerControllerTest {
//    private final static Long ID = 1L;
//    @Mock
//    private final CustomerService customerService;
//    @Mock
//    private final DepartmentService departmentService;
//    @Mock
//    private final CustomerConverter customerConverter;
//    @InjectMocks
//    private CustomerControllerTest customerController;
//
//    @Test
//    void getCustomer() {
//        final DepartmentDto department= mock(DepartmentDto.class);
//        final List<CustomerDto> customers = mock(List.class);
//        final WorkDayWithDepartmentIdDto workDay= mock(WorkDayWithDepartmentIdDto.class);
//
//        when(departmentService.findById(ID)).thenReturn(department);
//        when(departmentService.getDepartmentCustomers(ID)).thenReturn(customers);
//        when(departmentService.getWorkdayToday()).thenReturn(workDay);
//
//
//    }
//
//    @Test
//    void getCreateForm() {
//    }
//
//    @Test
//    void createNewCustomer() {
//    }
//
//    @Test
//    void getEditCustomerForm() {
//    }
//
//    @Test
//    void deleteCustomer() {
//    }
//
//    @Test
//    void editCustomer() {
//    }
//}