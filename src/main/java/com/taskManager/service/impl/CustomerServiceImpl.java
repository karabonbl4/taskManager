package com.taskManager.service.impl;

import com.taskManager.model.entity.Customer;
import com.taskManager.model.repository.CustomerRepository;
import com.taskManager.service.CustomerService;
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

}
