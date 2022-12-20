package com.taskManager.service.converter;

import com.taskManager.model.entity.Company;
import com.taskManager.service.dto.CustomerDto;
import com.taskManager.service.dto.ProviderDto;

public interface CompanyConverter {
    Company convertCustomerDtoToCompany(CustomerDto customerDto);
    Company convertProviderDtoToCompany(ProviderDto customerDto);
    CustomerDto convertCompanyToCustomerDto(Company company);
    ProviderDto convertCompanyToProviderDto(Company company);
}
