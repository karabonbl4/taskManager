package com.taskManager.service.impl;

import com.taskManager.model.repository.MaterialRepository;
import com.taskManager.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
}
