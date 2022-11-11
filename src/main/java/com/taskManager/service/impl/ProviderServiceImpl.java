package com.taskManager.service.impl;

import com.taskManager.model.repository.ProviderRepository;
import com.taskManager.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepository providerRepository;
}
