package com.taskManager.service;

import com.taskManager.model.entity.TempMaterial;

import java.util.List;

public interface TempMaterialService {
    TempMaterial save(TempMaterial tempMaterial);
    List<TempMaterial> saveAll(List<TempMaterial> tempMaterials);

}
