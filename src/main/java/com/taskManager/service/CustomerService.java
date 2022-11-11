package com.taskManager.service;

import com.taskManager.model.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findByDepartmentId(long id);
    Customer findByTaxNumber(Integer taxNumber);
    boolean save(Customer customer);
}
