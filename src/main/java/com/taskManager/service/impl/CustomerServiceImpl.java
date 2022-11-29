package com.taskManager.service.impl;

import com.taskManager.model.entity.Customer;
import com.taskManager.model.repository.CustomerRepository;
import com.taskManager.service.CustomerService;
import com.taskManager.service.converter.CustomerConverter;
import com.taskManager.service.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    @Override
    public Customer findByTaxNumber(Integer taxNumber) {
        return customerRepository.findByTaxNumber(taxNumber);
    }

    @Override
    public boolean save(Customer customer) {
        if (customer.getDepartmentId().getCustomers().stream()
                .anyMatch(existCustomer->existCustomer.getTaxNumber().equals(customer.getTaxNumber()))){
            return false;
        }
        customerRepository.saveAndFlush(customer);
        return true;
    }

    @Override
    public boolean update(CustomerDto customerDto) {
        var dbcustomer = customerRepository.getReferenceById(customerDto.getId());
        var customer = customerConverter.convertToCustomer(customerDto);
        if (customer.equals(dbcustomer)){
            return false;
        }
        customerRepository.saveAndFlush(customer);
        return true;
    }

    @Override
    public void delete(CustomerDto customerDto) {
        var dbCustomer = customerRepository.getReferenceById(customerDto.getId());
        dbCustomer.setName("deleted");
        customerRepository.saveAndFlush(dbCustomer);
    }

}
