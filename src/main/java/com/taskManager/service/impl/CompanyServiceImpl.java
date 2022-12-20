package com.taskManager.service.impl;

import com.taskManager.model.entity.Company;
import com.taskManager.model.repository.CompanyRepository;
import com.taskManager.service.CompanyService;
import com.taskManager.service.converter.CompanyConverter;
import com.taskManager.service.dto.CustomerDto;
import com.taskManager.service.dto.ProviderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyConverter companyConverter;
    private final CompanyRepository companyRepository;
    @Override
    public List<CustomerDto> findCustomerByDepartmentId(Long departmentId) {
        return companyRepository.findByBids_Products_Department_Id(departmentId).stream()
                .map(companyConverter::convertCompanyToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProviderDto> findProviderByDepartmentId(Long departmentId) {
        return companyRepository.findByBids_Materials_DepartmentMaterial_Id(departmentId).stream()
                .map(companyConverter::convertCompanyToProviderDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(Company company) {
        return false;
    }

    @Override
    public boolean update(Company company) {
        return false;
    }

    @Override
    public boolean delete(Company company) {
        return false;
    }
}
