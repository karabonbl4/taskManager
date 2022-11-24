package com.taskManager.service.impl;

import com.taskManager.model.entity.Provider;
import com.taskManager.model.repository.DepartmentRepository;
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
    private final DepartmentRepository departmentRepository;

    @Override
    public Provider findByTaxNumber(Integer taxNumber) {
        return providerRepository.findByTaxNumber(taxNumber);
    }

    @Override
    public boolean save(Provider provider) {
        if(findByTaxNumber(provider.getTaxNumber())!=null){
            return false;
        }
        providerRepository.save(provider);
        return true;
    }

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
        provider.setName(providerDto.getName());
        provider.setOwner(providerDto.getOwner());
        provider.setDepartmentProvider(departmentRepository.findById(providerDto.getDepartmentId()));
        provider.setTaxNumber(providerDto.getTaxNumber());
        provider.setLocation(providerDto.getLocation());
        provider.setEmail(providerDto.getEmail());
        return provider;
    }
}
