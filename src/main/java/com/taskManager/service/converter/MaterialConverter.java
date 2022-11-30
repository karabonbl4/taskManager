package com.taskManager.service.converter;

import com.taskManager.model.entity.Material;
import com.taskManager.service.dto.MaterialDto;

public interface MaterialConverter {
    MaterialDto convertToMaterialDto(Material material);
    Material convertToMaterial(MaterialDto materialDto);
}
