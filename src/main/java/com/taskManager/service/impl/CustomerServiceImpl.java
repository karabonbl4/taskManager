package com.taskManager.service.impl;

import com.taskManager.model.entity.Customer;
import com.taskManager.model.repository.CustomerRepository;
import com.taskManager.model.repository.DepartmentRepository;
import com.taskManager.service.CustomerService;
import com.taskManager.service.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public Customer findByTaxNumber(Integer taxNumber) {
        return customerRepository.findByTaxNumber(taxNumber);
    }

    @Override
    public boolean save(Customer customer) {
        if (departmentRepository.getReferenceById(customer.getDepartmentId().getId()).getCustomers().stream()
                .anyMatch(existCustomer->existCustomer.getTaxNumber().equals(customer.getTaxNumber()))){
            return false;
        }
        customerRepository.saveAndFlush(customer);
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

    @Override
    public boolean update(CustomerDto customerDto) {
        var dbcustomer = customerRepository.getReferenceById(customerDto.getId());
        var customer = convertToCustomer(customerDto);
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
