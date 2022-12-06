package com.taskManager.service.converter;

import com.taskManager.model.entity.Material;
import com.taskManager.model.entity.TempMaterial;
import com.taskManager.service.dto.MaterialDto;
import com.taskManager.service.dto.TempMaterialDto;

import java.util.List;

public interface MaterialConverter {
    MaterialDto convertToMaterialDto(Material material);
    Material convertToMaterial(MaterialDto materialDto);
    TempMaterial convertToTempMaterial(Material material);
    List<TempMaterial> convertToTempMaterial(String temMaterial);

}
