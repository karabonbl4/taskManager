package com.taskManager.service.converter;

import com.taskManager.model.entity.Provider;
import com.taskManager.service.dto.ProviderDto;

public interface ProviderConverter {
    ProviderDto convertToProviderDto(Provider provider);
    Provider convertToProvider(ProviderDto providerDto);
}
