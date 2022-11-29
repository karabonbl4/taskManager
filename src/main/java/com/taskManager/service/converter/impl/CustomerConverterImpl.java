package com.taskManager.service.converter.impl;

import com.taskManager.model.entity.Customer;
import com.taskManager.model.repository.DepartmentRepository;
import com.taskManager.service.converter.CustomerConverter;
import com.taskManager.service.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerConverterImpl implements CustomerConverter {
    private final DepartmentRepository departmentRepository;


    @Override
    public CustomerDto convertToCustomerDto(Customer customer) {
        var customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setTaxNumber(customer.getTaxNumber());
        customerDto.setEmail(customer.getEmail());
        customerDto.setLocation(customer.getLocation());
        customerDto.setOwner(customer.getOwner());
        customerDto.setDepartmentId(customer.getDepartmentId().getId());
        return customerDto;
    }

    @Override
    public Customer convertToCustomer(CustomerDto customerDto) {
        var customer = new Customer();
        if(customerDto.getId()!=null){
            customer.setId(customerDto.getId());
        }
        customer.setName(customerDto.getName());
        customer.setTaxNumber(customerDto.getTaxNumber());
        customer.setEmail(customerDto.getEmail());
        customer.setLocation(customerDto.getLocation());
        customer.setOwner(customerDto.getOwner());
        customer.setDepartmentId(departmentRepository.getReferenceById(customerDto.getDepartmentId()));
        return customer;
    }
}
