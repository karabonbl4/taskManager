package com.taskManager.service.converter.impl;

import com.taskManager.model.entity.Provider;
import com.taskManager.model.repository.DepartmentRepository;
import com.taskManager.service.converter.ProviderConverter;
import com.taskManager.service.dto.ProviderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProviderConverterImpl implements ProviderConverter {
    private final DepartmentRepository departmentRepository;
    @Override
    public ProviderDto convertToProviderDto(Provider provider) {
        var providerDto = new ProviderDto();
        providerDto.setId(provider.getId());
        providerDto.setName(provider.getName());
        providerDto.setDepartmentId(provider.getDepartmentProvider().getId());
        providerDto.setTaxNumber(provider.getTaxNumber());
        providerDto.setLocation(provider.getLocation());
        providerDto.setOwner(provider.getOwner());
        providerDto.setEmail(provider.getEmail());
        return providerDto;
    }

    @Override
    public Provider convertToProvider(ProviderDto providerDto) {
        var provider = new Provider();
        if(providerDto.getId()!=null){
            provider.setId(providerDto.getId());
        }
        provider.setName(providerDto.getName());
        provider.setOwner(providerDto.getOwner());
        provider.setDepartmentProvider(departmentRepository.getReferenceById(providerDto.getDepartmentId()));
        provider.setTaxNumber(providerDto.getTaxNumber());
        provider.setLocation(providerDto.getLocation());
        provider.setOwner(providerDto.getOwner());
        provider.setEmail(providerDto.getEmail());
        return provider;
    }
}
