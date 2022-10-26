package com.taskManager.service.impl;

import com.taskManager.model.repository.RoleRepository;
import com.taskManager.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
}
