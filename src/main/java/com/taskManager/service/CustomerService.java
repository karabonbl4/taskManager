package com.taskManager.service;

import com.taskManager.model.entity.Customer;
import com.taskManager.service.dto.CustomerDto;

public interface CustomerService {
    Customer findByTaxNumber(Integer taxNumber);

    boolean save(Customer customer);

    boolean update(CustomerDto customerDto);

    void delete(CustomerDto customerDto);
}
