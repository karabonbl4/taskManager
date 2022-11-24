package com.taskManager.service;

import com.taskManager.model.entity.Material;
import com.taskManager.service.dto.MaterialDto;

public interface MaterialService {
    boolean save(Material material);
    Material convertToMaterial(MaterialDto materialDto);
    MaterialDto convertToMaterialDto(Material material);
    boolean update(Material material);
    Material findById(Long id);

}
