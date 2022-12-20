package com.taskManager.service.converter.impl;

import com.taskManager.model.entity.Company;
import com.taskManager.model.enums.CompanyCategory;
import com.taskManager.service.converter.CompanyConverter;
import com.taskManager.service.dto.CustomerDto;
import com.taskManager.service.dto.ProviderDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyConverterImpl implements CompanyConverter {

    @Override
    public Company convertCustomerDtoToCompany(CustomerDto customerDto) {
        var company = new Company();
        if(customerDto.getId()!=null){
            company.setId(customerDto.getId());
        }
        company.setName(customerDto.getName());
        company.setTaxNumber(customerDto.getTaxNumber());
        company.setEmail(customerDto.getEmail());
        company.setLocation(customerDto.getLocation());
        company.setOwner(customerDto.getOwner());
        company.setRole(CompanyCategory.CUSTOMER);
        return company;
    }

    @Override
    public Company convertProviderDtoToCompany(ProviderDto providerDto) {
        var company = new Company();
        if(providerDto.getId()!=null){
            company.setId(providerDto.getId());
        }
        company.setName(providerDto.getName());
        company.setTaxNumber(providerDto.getTaxNumber());
        company.setEmail(providerDto.getEmail());
        company.setLocation(providerDto.getLocation());
        company.setOwner(providerDto.getOwner());
        company.setRole(CompanyCategory.PROVIDER);
        return company;
    }

    @Override
    public CustomerDto convertCompanyToCustomerDto(Company company) {
        var customerDto = new CustomerDto();
        customerDto.setId(company.getId());
        customerDto.setName(company.getName());
        customerDto.setTaxNumber(company.getTaxNumber());
        customerDto.setEmail(company.getEmail());
        customerDto.setLocation(company.getLocation());
        customerDto.setOwner(company.getOwner());
        return customerDto;
    }

    @Override
    public ProviderDto convertCompanyToProviderDto(Company company) {
        var providerDto = new ProviderDto();
        providerDto.setId(company.getId());
        providerDto.setName(company.getName());
        providerDto.setTaxNumber(company.getTaxNumber());
        providerDto.setEmail(company.getEmail());
        providerDto.setLocation(company.getLocation());
        providerDto.setOwner(company.getOwner());
        return providerDto;
    }
}
