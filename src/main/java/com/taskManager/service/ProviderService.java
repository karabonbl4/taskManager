package com.taskManager.service;

import com.taskManager.model.entity.Provider;
import com.taskManager.service.dto.ProviderDto;

public interface ProviderService {
    Provider findByTaxNumber(Integer taxNumber);
    boolean save(Provider provider);
    boolean update(ProviderDto providerDto);
    void delete(ProviderDto providerDto);
}
