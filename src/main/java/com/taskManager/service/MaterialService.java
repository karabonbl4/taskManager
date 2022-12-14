package com.taskManager.service;

import com.taskManager.model.entity.Material;
import com.taskManager.service.dto.MaterialDto;

public interface MaterialService {
    boolean save(Material material);

    boolean update(MaterialDto materialDto);

    void delete(MaterialDto materialDto);
    Material findById(Long id);
}
