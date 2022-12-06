package com.taskManager.service.impl;

import com.taskManager.model.entity.TempMaterial;
import com.taskManager.model.repository.TempMaterialRepository;
import com.taskManager.service.TempMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TempMaterialServiceImpl implements TempMaterialService {
    private final TempMaterialRepository tempMaterialRepository;
    @Override
    public TempMaterial save(TempMaterial tempMaterial) {
        return tempMaterialRepository.saveAndFlush(tempMaterial);
    }

    @Override
    public List<TempMaterial> saveAll(List<TempMaterial> tempMaterials) {
        return tempMaterialRepository.saveAllAndFlush(tempMaterials);
    }
}
