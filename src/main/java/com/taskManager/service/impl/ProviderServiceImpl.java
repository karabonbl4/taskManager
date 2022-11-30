package com.taskManager.service.impl;

import com.taskManager.model.entity.Provider;
import com.taskManager.model.repository.ProviderRepository;
import com.taskManager.service.ProviderService;
import com.taskManager.service.converter.ProviderConverter;
import com.taskManager.service.dto.ProviderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepository providerRepository;
    private final ProviderConverter providerConverter;

    @Override
    public Provider findByTaxNumber(Integer taxNumber) {
        return providerRepository.findByTaxNumber(taxNumber);
    }

    @Override
    public boolean save(Provider provider) {
        if (provider.getDepartmentProvider().getProviders().stream()
                .anyMatch(existProvider->existProvider.getTaxNumber().equals(provider.getTaxNumber()))){
            return false;
        }
        providerRepository.saveAndFlush(provider);
        return true;
    }

    @Override
    public boolean update(ProviderDto providerDto) {
        var dbprovider = providerRepository.getReferenceById(providerDto.getId());
        var provider = providerConverter.convertToProvider(providerDto);
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
