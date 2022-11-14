package com.taskManager.service.impl;

import com.taskManager.model.entity.Provider;
import com.taskManager.model.repository.ProviderRepository;
import com.taskManager.service.DepartmentService;
import com.taskManager.service.ProviderService;
import com.taskManager.service.dto.ProviderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepository providerRepository;
//    private final DepartmentService departmentService;

    @Override
    public ProviderDto convertToProviderDto(Provider provider) {
        var providerDto = new ProviderDto();
        providerDto.setId(provider.getId());
        providerDto.setName(provider.getName());
        providerDto.setDepartmentId(provider.getDepartmentProvider().getId());
        providerDto.setTaxNumber(provider.getTaxNumber());
        providerDto.setLocation(provider.getLocation());
        providerDto.setEmail(provider.getEmail());
        return providerDto;
    }

    @Override
    public Provider convertToProvider(ProviderDto providerDto) {
        var provider = new Provider();
        provider.setId(providerDto.getId());
        provider.setName(providerDto.getName());
//        provider.setDepartmentProvider(departmentService.convertToDepartment(departmentService.findById(providerDto.getDepartmentId())));
        provider.setTaxNumber(provider.getTaxNumber());
        provider.setLocation(provider.getLocation());
        provider.setEmail(provider.getEmail());
        return provider;
    }
}
