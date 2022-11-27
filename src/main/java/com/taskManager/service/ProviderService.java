package com.taskManager.service;

import com.taskManager.model.entity.Provider;
import com.taskManager.service.dto.ProviderDto;

public interface ProviderService {
    Provider findByTaxNumber(Integer taxNumber);
    boolean save(Provider provider);
    ProviderDto convertToProviderDto(Provider provider);
    Provider convertToProvider(ProviderDto providerDto);
    boolean update(ProviderDto providerDto);
}
