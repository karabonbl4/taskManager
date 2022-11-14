package com.taskManager.service;

import com.taskManager.model.entity.Provider;
import com.taskManager.service.dto.ProviderDto;

public interface ProviderService {
    ProviderDto convertToProviderDto(Provider provider);
    Provider convertToProvider(ProviderDto providerDto);

}
