package com.taskManager.service;

import com.taskManager.model.entity.Customer;
import com.taskManager.service.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<Customer> findByDepartmentId(long id);
    Customer findByTaxNumber(Integer taxNumber);
    boolean save(Customer customer);
    CustomerDto convertToCustomerDto(Customer customer);
    Customer convertToCustomer(CustomerDto customerDto);
}