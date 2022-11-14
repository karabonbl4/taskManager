package com.taskManager.service;

import com.taskManager.model.entity.Material;
import com.taskManager.service.dto.MaterialDto;

public interface MaterialService {
    Material convertToMaterial(MaterialDto materialDto);
    MaterialDto convertToMaterialDto(Material material);

}
