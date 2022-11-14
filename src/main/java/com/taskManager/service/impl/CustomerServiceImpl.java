package com.taskManager.service.impl;

import com.taskManager.model.entity.Customer;
import com.taskManager.model.repository.CustomerRepository;
import com.taskManager.service.CustomerService;
import com.taskManager.service.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> findByDepartmentId(long id) { //check condition of customer, because throw EX, that customer not found
        return customerRepository.findByDepartmentId(id);
    }

    @Override
    public Customer findByTaxNumber(Integer taxNumber) {
        return customerRepository.findByTaxNumber(taxNumber);
    }

    @Override
    public boolean save(Customer customer) {
        if (findByTaxNumber(customer.getTaxNumber())!=null){ //other condition
            return false;
        }
        customerRepository.save(customer);
        return true;
    }

    @Override
    public CustomerDto convertToCustomerDto(Customer customer) {
        var customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setTaxNumber(customer.getTaxNumber());
        customerDto.setEmail(customer.getEmail());
        customerDto.setLocation(customer.getLocation());
        customerDto.setDepartmentId(customer.getDepartmentId().getId());
        return customerDto;
    }

    @Override
    public Customer convertToCustomer(CustomerDto customerDto) {
        var customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setTaxNumber(customerDto.getTaxNumber());
        customer.setEmail(customerDto.getEmail());
        customer.setLocation(customerDto.getLocation());
//        customer.setDepartmentId(departmentService.getById(customerDto.getDepartmentId()));
        return customer;
    }

}
