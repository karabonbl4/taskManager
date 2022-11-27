package com.taskManager.service.impl;

import com.taskManager.model.entity.Provider;
import com.taskManager.model.repository.DepartmentRepository;
import com.taskManager.model.repository.ProviderRepository;
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
        if (departmentRepository.getReferenceById(provider.getDepartmentProvider().getId()).getCustomers().stream()
                .anyMatch(existProvider->existProvider.getTaxNumber().equals(provider.getTaxNumber()))){
            return false;
        }
        providerRepository.saveAndFlush(provider);
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
        if(providerDto.getId()!=null){
            provider.setId(providerDto.getId());
        }
        provider.setName(providerDto.getName());
        provider.setOwner(providerDto.getOwner());
        provider.setDepartmentProvider(departmentRepository.getReferenceById(providerDto.getDepartmentId()));
        provider.setTaxNumber(providerDto.getTaxNumber());
        provider.setLocation(providerDto.getLocation());
        provider.setEmail(providerDto.getEmail());
        return provider;
    }

    @Override
    public boolean update(ProviderDto providerDto) {
        var dbprovider = providerRepository.getReferenceById(providerDto.getId());
        var provider = convertToProvider(providerDto);
        if (provider.equals(dbprovider)){
            return false;
        }
        providerRepository.saveAndFlush(provider);
        return true;
    }

    @Override
    public void delete(ProviderDto providerDto) {
        var dbProvider = providerRepository.getReferenceById(providerDto.getId());
        dbProvider.setName("deleted");
        providerRepository.saveAndFlush(dbProvider);
    }
}
