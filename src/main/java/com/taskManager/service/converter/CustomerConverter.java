package com.taskManager.service.converter;

import com.taskManager.model.entity.Customer;
import com.taskManager.service.dto.CustomerDto;

public interface CustomerConverter {
    CustomerDto convertToCustomerDto(Customer customer);
    Customer convertToCustomer(CustomerDto customerDto);
}
