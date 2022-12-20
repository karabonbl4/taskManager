package com.taskManager.service;

import com.taskManager.model.entity.Company;
import com.taskManager.service.dto.CustomerDto;
import com.taskManager.service.dto.ProviderDto;

import java.util.List;

public interface CompanyService {
    List<CustomerDto> findCustomerByDepartmentId(Long departmentId);
    List<ProviderDto> findProviderByDepartmentId(Long departmentId);
    boolean save(Company company);
    boolean update(Company company);
    boolean delete(Company company);

}
