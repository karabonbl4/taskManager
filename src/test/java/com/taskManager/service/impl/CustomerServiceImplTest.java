package com.taskManager.service.impl;

import com.taskManager.model.entity.Customer;
import com.taskManager.model.entity.Department;
import com.taskManager.model.repository.CustomerRepository;
import com.taskManager.service.converter.CustomerConverter;
import com.taskManager.service.dto.CustomerDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
    private static final Integer TAX_NUMBER = 3366985;
    private static final Long ID = 1L;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerConverter customerConverter;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    @DisplayName("Find customer by tax number in repository")
    void findByTaxNumber_shouldCallRepository() {
        final Customer customer = mock(Customer.class);
        when(customerRepository.findByTaxNumber(TAX_NUMBER)).thenReturn(customer);

        final Customer actual = customerService.findByTaxNumber(TAX_NUMBER);

        assertNotNull(actual);
        assertEquals(customer, actual);
    }

    @Test
    @DisplayName("Save customer in repository")
    void save() {
        final Customer customer = mock(Customer.class);
        final Department department = mock(Department.class);
        final List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        when(customer.getDepartmentId()).thenReturn(department);
        when(department.getCustomers()).thenReturn(customers);
        lenient().when(customer.getTaxNumber()).thenReturn(TAX_NUMBER);
        boolean actual = customerService.save(customer);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Update customer")
    void update() {
        final Customer customer = mock(Customer.class);
        final CustomerDto customerDto = mock(CustomerDto.class);
        when(customerDto.getId()).thenReturn(ID);
        when(customerRepository.getReferenceById(ID)).thenReturn(customer);
        when(customerConverter.convertToCustomer(customerDto)).thenReturn(customer);
        boolean actual = customerService.update(customerDto);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Soft delete customer")
    void delete() {
        final Customer customer = mock(Customer.class);
        final CustomerDto customerDto = mock(CustomerDto.class);
        when(customerRepository.getReferenceById(ID)).thenReturn(customer);
        when(customerDto.getId()).thenReturn(ID);
        customerService.delete(customerDto);

        verify(customerRepository).saveAndFlush(customer);
    }
}